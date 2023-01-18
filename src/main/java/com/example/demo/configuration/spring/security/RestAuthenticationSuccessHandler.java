package com.example.demo.configuration.spring.security;

import com.example.demo.base.SystemCode;
import com.example.demo.domain.UserEventLog;
import com.example.demo.domain.event.UserEvent;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        private final ApplicationEventPublisher applicationEventPublisher;
        private final UserService userService;

        @Autowired
    public RestAuthenticationSuccessHandler(ApplicationEventPublisher applicationEventPublisher,UserService userService){
        this.applicationEventPublisher=applicationEventPublisher;
        this.userService=userService;
        }

    public void onAuthenticationSuccess(HttpServletResponse request, HttpServletResponse response, Authentication authentication)throws IOException, ServletException {
            Object object=authentication.getPrincipal();
            if (null!=object){
                User springUser=(User) object;
                com.example.demo.domain.User user=userService.getUserByUserName(springUser.getUsername());
                if (null!=user){
                    UserEventLog userEventLog=new UserEventLog(user.getId(),user.getUserName(),user.getRealName(),new Date());
                    userEventLog.setContent(user.getUserName()+"登录了学之思开源考试系统");
                    applicationEventPublisher.publishEvent(new UserEvent(userEventLog));
                    com.example.demo.domain.User newUser=new com.example.demo.domain.User();
                    newUser.setUserName(user.getUserName());
                    newUser.setImagePath(user.getImagePath());
                    RestUtil.response(response, SystemCode.OK.getCode(),SystemCode.OK.getMessage());
                }else{
                    RestUtil.response(response,SystemCode.UNAUTHORIZED.getCode(),SystemCode.UNAUTHORIZED.getMessage());
                }


            }
    }

}
