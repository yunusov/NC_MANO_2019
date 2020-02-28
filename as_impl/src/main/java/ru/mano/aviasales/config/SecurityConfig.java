package ru.mano.aviasales.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

    @Autowired
    private AuthProvider authProvider;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/registration*")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/admin*")
                .hasRole("ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers("/user", "/login/*")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().loginPage("/login")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources*",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars*");
    }
}
