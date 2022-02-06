package com.atguigu.mymall.admin.security;


import cn.hutool.json.JSONUtil;
import com.atguigu.mymall.admin.util.JwtUtils;
import com.atguigu.mymall.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author mingchiuli
 * @create 2022-01-24 10:09 PM
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        // 生成jwt，并放置到请求头中

        String username = authentication.getName();
        String jwt = jwtUtils.generateToken(username);
        response.setHeader(jwtUtils.getHeader(), jwt);

        Result result = Result.succ("");

        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();
    }

}
