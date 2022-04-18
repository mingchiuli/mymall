package com.atguigu.mymall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.mymall.product.service.CategoryBrandRelationService;
import com.atguigu.mymall.product.vo.Catelog2Vo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.mymall.product.entity.CategoryEntity;
import com.atguigu.mymall.product.service.CategoryService;
import com.atguigu.mymall.product.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
* @author mingchiuli
* @description 针对表【pms_category(商品三级分类)】的数据库操作Service实现
* @createDate 2022-02-16 09:37:18
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
    implements CategoryService {


    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public List<CategoryEntity> listWithTree() {
        //1.查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
        //2.组装父子的树形结构
        //2.1 找到所有一级分类
        return entities.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .peek(menu-> menu.setChildren(getChildrens(menu, entities)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 检查当前删除的菜单，是否被别的地方引用

        baseMapper.deleteBatchIds(asList);
    }

    @Override
    @Transactional
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
//        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());

    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    //递归 225
    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        //1 收集当前结点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }

    //递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream()
                .filter(categoryEntity -> Objects.equals(categoryEntity.getParentCid(), root.getCatId()))
                .peek(categoryEntity -> categoryEntity.setChildren(getChildrens(categoryEntity, all)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }



    /**
     * @Cacheable 表示这个东西是结果是需要缓存的，如果缓存中有，方法不调用；否则调用方法，放入缓存
     * value 每一个需要缓存的数据我们都要来指定要放在哪个名字的缓存里【缓存的分区(按照业务类型区分)】
     * 3)、默认行为
     *   1）、如果缓存中有 方法不用调用
     *   2）、 key默认生成 缓存的名字::simplekey 自动生成的key值
     *   3）、缓存的value值 默认使用java虚拟化机制 将序列化的数据存到redis
     *   4）、默认过期时间为-1
     *
     *  自定义
     *   1）、指定生成的缓存使用的key key的属性指定接受一个SpEL表达式
     *       SpEL表达式地址
     *   2)、指定缓存的数据存活时间 配置文件中修改TTL
     *   3）、将数据修改为json格式
     */ //sync = true解决缓存击穿 默认是false
    @Cacheable(value = {"category"}, key = "#root.method.name", sync = true)
    @Override
    public List<CategoryEntity> getLevel1Catrgorys() {

        //测试spring cache
        System.out.println("public List<CategoryEntity> getLevel1Catrgorys() {....");

        //压力测试  数据库navicat增加了 parent_cid 为索引
//        long l = System.currentTimeMillis();
        List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
//        System.out.println("消耗时间->：" + (System.currentTimeMillis()-l) + "ms");
        return categoryEntities;
//        return null;//测试缓存空值
    }


    private Map<String, List<Catelog2Vo>> getDataFromDB() {
        String catalogJSON = redisTemplate.opsForValue().get("catalogJSON");
        if (!StringUtils.isEmpty(catalogJSON)) {
            //如果缓存不为null，直接可以返回
            Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
            });
            return result;
        }
        System.out.println("查询了数据库。。。。。。。");
        List<CategoryEntity> selectList = baseMapper.selectList(null);

        //1 查出所有1级分类
        List<CategoryEntity> level1Catrgorys = getParent_cid(selectList, 0L);

        //2 封装分类
        Map<String, List<Catelog2Vo>> parent_cid = level1Catrgorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1 拿到每一个1级分类 查到这个1级分类的2级分类
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());
            //2 封装上面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    //1 找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = getParent_cid(selectList, l2.getCatId());
                    if (level3Catelog != null) {
                        List<Catelog2Vo.Catalog3Vo> collect = level3Catelog.stream().map(l3 -> {
                            //2 封装成指定格式
                            Catelog2Vo.Catalog3Vo catalog3Vo = new Catelog2Vo.Catalog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catalog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));

        //3 查到的数据库再放入缓存， 将对象转为json放在缓存中
        String jsonString = JSON.toJSONString(parent_cid);
        redisTemplate.opsForValue().set("catalogJSON", jsonString, 1, TimeUnit.DAYS);//1天过期
        return parent_cid;
    }


    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithRedisLock() {

        //1 抢占分布式锁 去redis占坑
        String uuid = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 300, TimeUnit.SECONDS);
        if (lock) {
            System.out.println("获取分布式锁成功......");
            //加锁成功 占到了坑位 ---> 执行业务
            //2 设置过期时间  -- 30s 必须和加锁是同步的 原子的
//            redisTemplate.expire("lock", 30, TimeUnit.MINUTES);
            Map<String, List<Catelog2Vo>> dataFromDB;
            try {
                dataFromDB = getDataFromDB();//业务代码
            }finally {
                String script = "if redis.call('get',KEYS[1]) == ARGV[1]  then return redis.call('del',KEYS[1]) else return 0 end";
                //删除锁
                Long lock1 = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList("lock"), uuid);
            }

            //获取对比值 + 对比成功删除 = 原子操作  lua脚本解锁
//            String lockValue = redisTemplate.opsForValue().get("lock");
//            if (uuid.equals(lockValue)) {
//                //删除我自己的锁
//                redisTemplate.delete("lock");//删除锁
//            }

            return dataFromDB;
        }else {
            //加锁失败 ----> 重试synchronized()
            //次数多，就让其休眠
            System.out.println("获取分布式锁不成功......等待重试");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getCatalogJsonFromDbWithRedisLock();//自旋的方式
        }
    }


    @Override
    @Cacheable(value = "category", key = "#root.methodName")
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        System.out.println("查询数据库...");
        List<CategoryEntity> selectList = baseMapper.selectList(null);

        //1 查出所有1级分类
        List<CategoryEntity> level1Catrgorys = getParent_cid(selectList, 0L);

        //2 封装分类
        Map<String, List<Catelog2Vo>> parent_cid = level1Catrgorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1 拿到每一个1级分类 查到这个1级分类的2级分类
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());
            //2 封装上面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    //1 找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = getParent_cid(selectList, l2.getCatId());
                    if (level3Catelog != null) {
                        List<Catelog2Vo.Catalog3Vo> collect = level3Catelog.stream().map(l3 -> {
                            //2 封装成指定格式
                            Catelog2Vo.Catalog3Vo catalog3Vo = new Catelog2Vo.Catalog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catalog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));
        return parent_cid;
    }

    //TODO 产生堆外内存溢出：OutOfDirectMemoryError
    //1)、Springboot2.0以后默认使用Lettuce作为操作redis的客户端。它使用netty进行网络通信。
    //2), Lettuce的bug导致netty堆外内存溢出 -Xmx300m; netty如果没有指定堆外内存，默认使用Xmx300m
    //      可以通过-Dio.netty.maxDirectMemory进行设置
    //解决方案  不能使用-Dio.netty.maxDirectMemory只是去调大堆外内存。
    //        1)、升级Lettuce客户端。 2),切换使用jedis
//    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson2() {
        //给缓存中放入json字符串，拿出的json字符串，还要逆转为能用的对象【序列化与反序列化】

        /**
         * 1、空结果缓存:解决缓存穿透
         * 2、设置过期时间(加随机值):解决缓存雪崩
         * 3、加锁;解决缓存击穿
         */

        //1 加入缓存逻辑， 以后缓存中存放的都是json字符串   json跨平台、跨语言兼容
        String catalogJson = redisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isEmpty(catalogJson)) {
            //2 缓存中没有，查询数据库。
            System.out.println("缓存不命中！。。。。查询数据库。。");
//            Map<String, List<Catelog2Vo>> catalogJsonFromDb = getCatalogJsonFromDbWithRedissonLock();
//            return catalogJsonFromDb;
        }
        System.out.println("缓存命中！。。。。直接返回。");

        //转为指定的对象
        Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJson, new TypeReference<Map<String, List<Catelog2Vo>>>() {});
        return result;
    }

    private List<CategoryEntity> getParent_cid(List<CategoryEntity> selectList, Long parent_cid) {
        List<CategoryEntity> collect = selectList.stream().filter(item -> {
            return item.getParentCid() == parent_cid;
        }).collect(Collectors.toList());
//        return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", v.getCatId()));
        return collect;
    }
}




