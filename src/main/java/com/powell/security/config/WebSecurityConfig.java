package com.powell.security.config;

import com.powell.security.AjaxAuthenticationProvider;
import com.powell.security.AjaxAwareAuthenticationSuccessHandler;
import com.powell.security.JsonLoginProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String APP_RESOURCES = "/my-shift/src/**";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/login2";

    public static final String NODE_MODULE_RESOURCES = "/my-shift/node_modules/**";

    @Autowired
    private AjaxAwareAuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    protected JsonLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        JsonLoginProcessingFilter filter = new JsonLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

//    @Bean
//    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
//        List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT, FORM_BASED_LOGIN_ENTRY_POINT);
//        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
//        JwtTokenAuthenticationProcessingFilter filter
//                = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
//        filter.setAuthenticationManager(this.authenticationManager);
//        return filter;
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
    }

    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(APP_RESOURCES,
                        NODE_MODULE_RESOURCES,
                        "/typings/**",
                        "/*.css",
                        "/dist/css/*.css",
                        "/*.json",
                        "/registerAdmin",
                        "/employeeRegistration",
                        "/my-shift/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/")
                .permitAll()
                .and()
                .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();

    }
}