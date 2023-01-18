package com.example.demo.configuration.spring.security;

import com.example.demo.configuration.property.CookieConfig;
import com.example.demo.configuration.property.SystemConfig;
import com.example.demo.domain.enums.RoleEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {


@Configuration
public static class FormLoginWebSecurityConfigureAdapter extends WebSecurityConfigurerAdapter {
    private final SystemConfig systemConfig;
    private final LoginAuthenticationEntryPoint loginAuthenticationEntryPoint;
    private final RestAuthenticationProvider restAuthenticationProvider;
    private final RestDetialsServiceImpl restDetialsService;
    private final RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    private final RestAuthenticationFailureHandler restAuthenticationFailureHandler;
    private final RestLogoutSuccessHandler restLogoutSuccessHandler;
    private final RestAccessDeniedHandler restAccessDeniedHandler;


    public FormLoginWebSecurityConfigureAdapter(SystemConfig systemConfig, LoginAuthenticationEntryPoint loginAuthenticationEntryPoint, RestAuthenticationProvider restAuthenticationProvider, RestDetialsServiceImpl restDetialsService, RestAuthenticationSuccessHandler restAuthenticationSuccessHandler, RestAuthenticationFailureHandler restAuthenticationFailureHandler, RestLogoutSuccessHandler restLogoutSuccessHandler, RestAccessDeniedHandler restAccessDeniedHandler) {
        this.systemConfig = systemConfig;
        this.loginAuthenticationEntryPoint = loginAuthenticationEntryPoint;
        this.restAuthenticationProvider = restAuthenticationProvider;
        this.restDetialsService = restDetialsService;
        this.restAuthenticationSuccessHandler = restAuthenticationSuccessHandler;
        this.restAuthenticationFailureHandler = restAuthenticationFailureHandler;
        this.restLogoutSuccessHandler = restLogoutSuccessHandler;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        List<String> securityIgnoreUrls = systemConfig.getSecurityIgnoreUrls();
        String[] ignores = new String[securityIgnoreUrls.size()];

        http
                .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(loginAuthenticationEntryPoint)
                .and().authenticationProvider(restAuthenticationProvider)
                .authorizeRequests()
                .antMatchers(securityIgnoreUrls.toArray(ignores)).permitAll()
                .antMatchers("/api/admin/*").hasRole(RoleEnum.ADMIN.getRoleName())
                .antMatchers("/api/student/*").hasRole(RoleEnum.STUDENT.getRoleName())
                .anyRequest().permitAll()
                .and().exceptionHandling().accessDeniedHandler(restAccessDeniedHandler)
                .and().formLogin().successHandler(restAuthenticationSuccessHandler).failureHandler(restAuthenticationFailureHandler)
.and().logout().logoutUrl("/api/user/logout").logoutSuccessHandler(restLogoutSuccessHandler).invalidateHttpSession(true)
.and().rememberMe().key(CookieConfig.getName()).tokenValiditySeconds(CookieConfig.getInterval()).userDetailsService(restDetialsService)
.and().csrf().disable()
.cors();

    }

    public CorsConfigurationSource corsConfigurationSource(){
        final CorsConfiguration configuration=new CorsConfiguration();
        configuration.setMaxAge(3600L);
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
configuration.setAllowCredentials(true);
configuration.setAllowedHeaders(Collections.singletonList("*"));
final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
source.registerCorsConfiguration("/api/*",configuration);
return source;

    }


    public RestLoginAuthenticationFilter authenticationFilter()
            throws  Exception{
    RestLoginAuthenticationFilter authenticationFilter=new RestLoginAuthenticationFilter();
    authenticationFilter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
    authenticationFilter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);
    authenticationFilter.setAuthenticationManager(authenticationManagerBean());
    authenticationFilter.setUserDetailsService(restDetialsService);
       return authenticationFilter;
}

}
}