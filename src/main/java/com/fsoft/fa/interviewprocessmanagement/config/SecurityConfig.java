package com.fsoft.fa.interviewprocessmanagement.config;

import com.fsoft.fa.interviewprocessmanagement.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailService userDetailService;

    @Autowired
    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        String[] ignored = {
                "/resources/**",
                "/static/**",
                "/uploads/**",
                "/css/**",
                "/fonts/**",
                "/cms/**",
                "/js/**",
                "/images/**",
        };
        web.ignoring().antMatchers(ignored);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off

        http.csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/").permitAll()
				.antMatchers("/chitiet").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/downloadFile").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/home")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/home?error")
                .defaultSuccessUrl("/management_portal")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .and()
            .exceptionHandling().accessDeniedPage("/403");
        // @formatter:on
    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }
}
