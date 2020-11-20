package com.pactera.esc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(loginAuthenticationProvider(passwordEncoder()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .configurationSource(getConfigurationSource())
            .and()
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(new AjaxAuthenticationEntryPoint())
            .and().authorizeRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .antMatchers("/api/**").authenticated()
            .anyRequest().permitAll()
            //自定义退出的地址
            .and().logout().logoutUrl("/api/system/logout")
            .logoutSuccessHandler(new LogoutAuthenticationSuccessHandler())
            // 删除当前的JSESSIONID
            .deleteCookies("JSESSIONID").and()
            .addFilterBefore(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
        LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new AjaxAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new AjaxAuthenticationFailureHandler());
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers("/swagger-resources/**")
            .antMatchers("/swagger-ui.html")
            .antMatchers("/webjars/**")
            .antMatchers("/v2/**")
            .antMatchers("/h2-console/**");
//            .antMatchers("**");
    }

    @Bean
    public AuthenticationProvider loginAuthenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new LoginAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    private CorsConfigurationSource getConfigurationSource() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }



}
