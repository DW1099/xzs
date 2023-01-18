package com.example.demo.configuration.spring.security;

import com.example.demo.configuration.property.CookieConfig;
import com.example.demo.utility.JsonUtil;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class RestLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private final org.slf4j.Logger logger= LoggerFactory.getLogger(RestLoginAuthenticationFilter.class);

  public RestLoginAuthenticationFilter(){
        super(new AntPathRequestMatcher("/api/user/login","POST"));
  }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        UsernamePasswordAuthenticationToken authRequest;
        try(InputStream is=request.getInputStream()){
            AuthenticationBean authenticationBean= JsonUtil.toJsonObject(is,AuthenticationBean.class);
            request.setAttribute(TokenBasedRememberMeServices.DEFAULT_PARAMETER,authenticationBean.isRemember());
            authRequest=new UsernamePasswordAuthenticationToken(authenticationBean.getUserName(),authenticationBean.getPassword());
        }catch(IOException e){
        logger.error(e.getMessage(),e);
        authRequest=new UsernamePasswordAuthenticationToken("","");
        }
        setDetails(request,authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    void setUserDetailsService(UserDetailsService userDetailsService){
        RestTokenBasedRememberMeService tokenBasedRememberMeService=new RestTokenBasedRememberMeService(CookieConfig.getName(),userDetailsService);
        tokenBasedRememberMeService.setTokenValiditySeconds(CookieConfig.getInterval());
        setRememberMeServices(tokenBasedRememberMeService);
    }

    public void setDetails(HttpServletRequest request,UsernamePasswordAuthenticationToken authenticationToken){
        authenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
