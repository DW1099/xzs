package com.example.demo.configuration.spring.mvc;

import com.example.demo.configuration.spring.wx.TokenHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private final TokenHandlerInterceptor tokenHandlerInterceptor;

}
