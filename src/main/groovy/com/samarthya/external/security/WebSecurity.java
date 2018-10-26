package com.samarthya.external.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring security 5.1
 *
 */
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/friend").anonymous().and()
//                .authorizeRequests().antMatchers("/friend").permitAll().and().csrf().disable();
        http.authorizeRequests().and()
                .csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET)
                .anonymous()
                .antMatchers(HttpMethod.POST,"/friend/**")
                .anonymous()
                .antMatchers(HttpMethod.DELETE,"/friend/**")
                .anonymous()
                .antMatchers(HttpMethod.PUT,"/friend/**")
                .anonymous()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        Map encoders = new HashMap();
//        encoders.put("noop", NoOpPasswordEncoder.getInstance());
//        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
//        encoders.put("SHA256", new StandardPasswordEncoder());
//        encoders.put("SHA512", new MessageDigestPasswordEncoder("SHA-512"));
//        encoders.put("SHA", new StandardPasswordEncoder());
//        encoders.put("MD5", new MessageDigestPasswordEncoder("SHA-1"));

        DelegatingPasswordEncoder dpe = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //PasswordEncoder dpe = new DelegatingPasswordEncoder("SHA", encoders);
        MyPasswordEncoder mpe = new MyPasswordEncoder();

        auth.ldapAuthentication()
                .userDnPatterns("uid={0},ou=People")
                .userSearchBase("ou=People,dc=saurabh,dc=com")
                //.groupSearchBase("memberOf")
                .userSearchFilter("uid={0}")
                .passwordCompare()
                .passwordEncoder(mpe)//new MessageDigestPasswordEncoder("SHA-512"))
                .passwordAttribute("userPassword").and().contextSource()
                .url("ldap://10.131.137.61:389/dc=saurabh,dc=com").and().contextSource().managerDn("cn=manager,dc=saurabh,dc=com").managerPassword("firewall");
    }

//    @Bean
//    public DefaultSpringSecurityContextSource contextSource() {
//        return new DefaultSpringSecurityContextSource(Collections.singletonList("ldap://localhost:12345"), "dc=saurabh,dc=com");
//    }
}
