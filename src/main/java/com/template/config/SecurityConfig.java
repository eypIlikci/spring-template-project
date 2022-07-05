package com.template.config;


import com.template.config.helper.CustomAuthenticationEntryPoint;
import com.template.config.helper.TokenFilter;
import com.template.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired
    private TokenFilter tokenFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
        http
                .authorizeRequests()
                .antMatchers("/api/home/**").permitAll()
                .antMatchers("/api/user/**").hasAnyAuthority(Role.USER.getAuthority())
                .antMatchers("/api/admin/**").hasAnyAuthority(Role.ADMIN.getAuthority());

        http.authorizeRequests().anyRequest().permitAll()
                .and()
                .userDetailsService(userDetailsService());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();//encoder is not applied
    }
}
