package com.atguigu.mymall.ware.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.atguigu.mymall.ware.mapper.WareInfoMapper;
import com.atguigu.mymall.common.utils.PageUtils;
import com.atguigu.mymall.common.utils.Query;
import com.atguigu.mymall.common.utils.R;
import com.atguigu.mymall.ware.mapper.WareInfoMapper;
import com.atguigu.mymall.ware.entity.WareInfoEntity;
import com.atguigu.mymall.ware.feign.MemberFeignService;
import com.atguigu.mymall.ware.service.WareInfoService;
import com.atguigu.mymall.ware.vo.FareVo;
import com.atguigu.mymall.ware.vo.MemberAddressVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Map;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfoEntity> implements WareInfoService {

    @Autowired
    MemberFeignService memberFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareInfoEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.eq("id", key)
                    .or().like("name", key)
                    .or().like("address", key)
                    .or().like("areacode", key);
        }

        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params), wrapper
        );

        return new PageUtils(page);
    }

    /**
     * 根据用户的收货地址计算运费
     */
    @Override
    public FareVo getFare(Long addrId) {

        //要返回的大对象
        FareVo fareVo = new FareVo();

        //远程查询用户地址信息
        R r = memberFeignService.addrInfo(addrId);
        MemberAddressVo data = r.getData("memberReceiveAddress", new TypeReference<MemberAddressVo>() {
        });

        if (data != null) {
            //FareVo第1个属性
            fareVo.setAddress(data);
            //简单处理 手机号末位当作运费
            String phone = data.getPhone();
            //123456789 9
            String substring = phone.substring(phone.length() - 1, phone.length());
            BigDecimal fare = new BigDecimal(substring);
            //FareVo第2个属性
            fareVo.setFare(fare);

            return fareVo;
        }
        return null;
    }
}
