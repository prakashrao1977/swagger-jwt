package com.test.microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.test.microservice.security.CustomUserDetailsService;
import com.test.microservice.security.JwtAuthenticationEntryPoint;
import com.test.microservice.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception
    {
        // httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/v1/auth/login","/swagger-ui.html**", "/swagger-resources/**", "/webjars/**", "/v2/api-docs/**","favicon.ico").permitAll().
        // httpSecurity.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, "/", "/configuration/**","/*.html","/favicon.ico", "/**/*.html","/**/*.css","/**/*.js","/**", "/error", "/api/v1/auth/login","/swagger-ui.html**", "/swagger-resources/**", "/webjars/**", "/v2/api-docs/**","/**").permitAll().antMatchers("/auth/**").permitAll().
        // httpSecurity.csrf().disable().authorizeRequests().antMatchers("/configuration/**","/*.html","/favicon.ico", "/**/*.html","/**/*.css","/**/*.js","/error", "/api/v1/auth/login","/swagger-ui.html**", "/swagger-resources/**", "/webjars/**", "/v2/api-docs/**").permitAll().antMatchers("/auth/**").permitAll().
        httpSecurity.csrf().disable().authorizeRequests().antMatchers("/favicon.ico", "/api/v1/auth/login","/swagger-ui.html**", "/swagger-resources/**", "/webjars/**", "/v2/api-docs/**").permitAll().antMatchers("/auth/**").permitAll().        
        anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint); // .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        // httpSecurity.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, "/", "/configuration/**","/*.html","/favicon.ico", "/**/*.html","/**/*.png","/**/*.jpeg","/**/*.jpg","/**/*.css","/**/*.js","/**", "/error", "/api/v1/auth/login","/v3/api-docs","/swagger-ui.html**", "/swagger-resources/**", "/webjars/**", "/v2/api-docs/**","/**").permitAll().antMatchers("/auth/**").permitAll().
        // anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(
        //    jwtAuthenticationEntryPoint);
        
        httpSecurity.addFilterBefore(
            jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class);
        
        // httpSecurity.headers().cacheControl();
    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder ()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean () throws Exception
    {
        return super.authenticationManagerBean();
    }
}
