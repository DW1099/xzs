package com.example.demo.configuration.spring.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.servlet.http.HttpServletRequest;

public class RestTokenBasedRememberMeService extends TokenBasedRememberMeServices {
    public RestTokenBasedRememberMeService(String key, UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }

    @Override
    protected boolean rememberMeRequested(HttpServletRequest request,String parameter){
        return (boolean)request.getAttribute(DEFAULT_PARAMETER);
    }
}
