package com.powell.security.config;

import com.powell.security.AjaxAuthenticationProvider;
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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String APP_RESOURCES = "/app/**";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/login2";

    public static final String NODE_MODULE_RESOURCES = "/node_modules/**";

    @Autowired
    private AuthenticationSuccessHandler successHandler;
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
                        "/systemjs.config.js",
                        "/typings/**",
                        "/*.css",
                        "/*.json"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/")
                .permitAll()
                .and()
                .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();

        ;
//                .and()
//                .logout()
//                .permitAll();

//        http
//                .csrf().disable() // We don't need CSRF for JWT based authentication
//                .exceptionHandling()
//                .authenticationEntryPoint(this.authenticationEntryPoint)
//
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .authorizeRequests()
//                .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll() // Login end-point
//                .antMatchers(APP_RESOURCES).permitAll()
//                .antMatchers(NODE_MODULE_RESOURCES).permitAll()
//                .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll() // Token refresh end-point
//                .antMatchers("/console").permitAll() // H2 Console Dash-board - only for testing
//                .and()
//                .authorizeRequests()
//                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated() // Protected API End-points

    }
}