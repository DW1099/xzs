package com.example.demo.configuration.spring.wx;

import com.example.demo.context.WxContext;
import com.example.demo.service.UserService;
import com.example.demo.service.UserTokenService;
import org.springframework.web.servlet.HandlerInterceptor;

public class TokenHandlerInterceptor implements HandlerInterceptor {

    private final UserTokenService userTokenService;
    private final UserService userService;
    private final WxContext wxContext;

    public TokenHandlerInterceptor(UserTokenService userTokenService, UserService userService, WxContext wxContext) {
        this.userTokenService = userTokenService;
        this.userService = userService;
        this.wxContext = wxContext;
    }
}
