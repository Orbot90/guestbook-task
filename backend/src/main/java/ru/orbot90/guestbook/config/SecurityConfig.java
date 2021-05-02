package ru.orbot90.guestbook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.orbot90.guestbook.model.User;

import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Qualifier("guestbookUserDetails") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().anonymous()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(this.successHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    private AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            String userName = String.valueOf(authentication.getPrincipal());
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            User user = new User();
            user.setName(userName);
            user.setRoles(roles);

            response.setContentType("application/json");
            try (PrintWriter writer = response.getWriter()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(writer, user);
            }

            // TODO: return JWT token if there's time to implement it, instead of just user data
        };
    }


}
