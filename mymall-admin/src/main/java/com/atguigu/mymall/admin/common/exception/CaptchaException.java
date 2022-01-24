package com.atguigu.mymall.admin.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author mingchiuli
 * @create 2022-01-24 9:52 PM
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }
}
