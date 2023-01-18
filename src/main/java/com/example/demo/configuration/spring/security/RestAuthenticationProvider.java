package com.example.demo.configuration.spring.security;


import com.example.demo.context.WebContext;

import com.example.demo.domain.enums.RoleEnum;
import com.example.demo.domain.enums.UserStatusEnum;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RestAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final WebContext webContext;

    @Autowired
    public RestAuthenticationProvider(AuthenticationService authenticationService,WebContext webContext,UserService userService){
        this.authenticationService=authenticationService;
        this.userService=userService;
        this.webContext=webContext;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=(String)authentication.getCredentials();

        com.example.demo.domain.User user=userService.getUserByUserName(username);
            if (user==null){
                throw new UsernameNotFoundException("用户名密码错误");
            }
            boolean result=authenticationService.authUser(user,username,password);
            if (!result){
                throw new BadCredentialsException("用户名或密码错误");
            }
            UserStatusEnum userStatusEnum=UserStatusEnum.fromCode(user.getStatus());
            if (UserStatusEnum.Disable==userStatusEnum){
                throw new LockedException("用户被禁用");
            }
         ArrayList<GrantedAuthority> grantedAuthorities=new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(RoleEnum.fromCode(user.getRole()).getRoleName()));
         User authUser=new User(user.getUserName(),user.getPassword(),grantedAuthorities);
            return new UsernamePasswordAuthenticationToken(authUser,authUser.getPassword(),authUser.getAuthorities());
    }

        @Override
        public boolean supports(Class<?> authentication) {
        return true;
    }
}
