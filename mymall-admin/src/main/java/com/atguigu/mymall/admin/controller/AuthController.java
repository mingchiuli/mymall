package com.atguigu.mymall.admin.controller;

import cn.hutool.core.lang.UUID;
import com.atguigu.mymall.common.utils.R;
import com.google.code.kaptcha.Producer;
import com.atguigu.mymall.admin.common.lang.Const;
import com.atguigu.mymall.admin.entity.SysUser;
import com.atguigu.mymall.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.mymall.admin.util.RedisUtil;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.Objects;

/**
 * @author mingchiuli
 * @create 2022-01-25 4:45 PM
 */


@RestController
public class AuthController extends BaseController {

    @Autowired
    HttpServletRequest req;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    Producer producer;

    @GetMapping("/captcha")
    public R captcha() throws IOException {

        String key = UUID.randomUUID().toString();
        String code = producer.createText();


        // 为了测试
		key = "aaaaa";
		code = "11111";

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        Base64.Encoder encoder = Base64.getEncoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encodeToString(outputStream.toByteArray());

        redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);

        return R.ok().put("token", key)
                .put("captchaImg", base64Img);
    }

    /**
     * 获取用户信息接口
     * @param principal
     * @return
     */
    @GetMapping("/sys/userInfo")
    public R userInfo(Principal principal) {

        SysUser sysUser = sysUserService.getByUsername(principal.getName());

        return R.ok().put("id", sysUser.getUserId())
                .put("username", sysUser.getUsername())
                .put("created", sysUser.getCreateTime());
    }

    @GetMapping("/test")
    public R test() {
        SysUser user = sysUserService.getById(1);

        return R.ok().put("user", user);

    }


}
