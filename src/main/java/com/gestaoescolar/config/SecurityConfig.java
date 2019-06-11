package com.gestaoescolar.config;

import com.gestaoescolar.service.securityConfig.UsuarioDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.SecureRandom;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UsuarioDetailsServiceImpl usuarioDetailsService;

    private static String REALM="MY_TEST_REALM";

    private static final String[] ignore = {
            "/h2-console/h2-console/login.do?jsessionid=b0f1118a163bfe7c56ec0101e3fdbe7f/**",
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



        http.authorizeRequests().antMatchers(ignore).
                permitAll().anyRequest().authenticated();

        http.csrf().disable().cors().disable().headers().frameOptions()
                .disable()
                .and()
                .formLogin()
                .failureUrl("/login?error")
                .defaultSuccessUrl("/imel/inicio")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("remember-me").permitAll().and().rememberMe();
/*

        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .formLogin()
                .failureUrl("/login?error")
                .defaultSuccessUrl("/imel/inicio")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("remember-me").permitAll().and().rememberMe();

      http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers(ignore)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .failureUrl("/login?error")
                .defaultSuccessUrl("/imel/inicio")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("remember-me").permitAll().and().rememberMe();*/

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/resources/**", "/assets/**", "/static/**","/plugins/**","/css/**", "/fonts/**","/scripts/**","/vendor/**", "/img/**","/js/**");
    }
}
