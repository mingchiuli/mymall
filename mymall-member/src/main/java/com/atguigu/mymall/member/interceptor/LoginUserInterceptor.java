package com.atguigu.mymall.member.interceptor;

import com.atguigu.mymall.common.constant.AuthServiceConstant;
import com.atguigu.mymall.common.vo.MemberResVo;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 孟享广
 * @date 2021-02-07 1:15 下午
 * @description
 */
@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<MemberResVo> loginUser = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean match = new AntPathMatcher().match("/member/**", request.getRequestURI());
        if (match) {
            return true;
        }

        HttpSession session = request.getSession();
        MemberResVo attribute = (MemberResVo) session.getAttribute(AuthServiceConstant.LOGIN_USER);
        if (attribute != null) {
            //说明登录了 放行
            loginUser.set(attribute);
            return true;
        } else {
            //没登录 去登录页 来拦截
            session.setAttribute("msg", "请先登录～～");
            response.sendRedirect("http://auth.localhost/login.html");
            return false;
        }
    }
}
