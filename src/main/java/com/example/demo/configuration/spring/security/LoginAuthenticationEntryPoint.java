package com.example.demo.configuration.spring.security;

import com.example.demo.base.SystemCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public final class LoginAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {



    public LoginAuthenticationEntryPoint() {
        super("/api/user/login");
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)throws IOException, ServletException{
            RestUtil.response(response, SystemCode.UNAUTHORIZED);


    }




}
