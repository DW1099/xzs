package com.example.demo.configuration.spring.security;


import com.example.demo.domain.enums.RoleEnum;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RestDetialsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public RestDetialsServiceImpl (UserService userService){
        this.userService=userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.demo.domain.User user=userService.getUserByUserName(username);
        if (user==null){
            throw new UsernameNotFoundException("Username not found.");
        }
        ArrayList<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(RoleEnum.fromCode(user.getRole()).getRoleName()));
        return new User(user.getUserName(),user.getPassword(),grantedAuthorities);
    }
}
