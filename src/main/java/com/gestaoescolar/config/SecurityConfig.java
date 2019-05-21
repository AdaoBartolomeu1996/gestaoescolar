package com.gestaoescolar.config;

import com.gestaoescolar.service.securityConfig.UsuarioDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/

import java.security.SecureRandom;


//@Configuration
//@EnableWebSecurity
public class SecurityConfig{
    /*
}
        extends WebSecurityConfigurerAdapter {


    @Autowired
    private UsuarioDetailsServiceImpl usuarioDetailsService;


    private static String REALM="MY_TEST_REALM";



    private static final String[] ignore = {
            "/h2-console/login.do?jsessionid=39fdf67143751f6bb790efe3bb367053/**",
            "/h2-console/**"
    };

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12, new SecureRandom(REALM.getBytes()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(ignore)
                .permitAll()
                .anyRequest()
                .authenticated();



    }*/

}
