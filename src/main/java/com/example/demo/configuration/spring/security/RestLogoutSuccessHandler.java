package com.example.demo.configuration.spring.security;

import com.example.demo.base.SystemCode;
import com.example.demo.domain.UserEventLog;
import com.example.demo.domain.event.UserEvent;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class RestLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
private final ApplicationEventPublisher eventPublisher;
private final UserService userService;


@Autowired
    public RestLogoutSuccessHandler(ApplicationEventPublisher eventPublisher,UserService userService){
    this.eventPublisher=eventPublisher;
    this.userService=userService;
}

    @Autowired
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        User springuser=(User)authentication.getPrincipal();
        if (null!=springuser){
            com.example.demo.domain.User  user=userService.getUserByUserName(springuser.getUsername());
            UserEventLog userEventLog=new UserEventLog(user.getId(),user.getUserName(),user.getRealName(),new Date());
            userEventLog.setContent(user.getUserName()+"登出学之思开源考试系统");
            eventPublisher.publishEvent(new UserEvent(userEventLog));
        }
        RestUtil.response(response, SystemCode.OK);
    }

}
