package com.codingapple.javatest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests((authorize) ->
                authorize
                    .requestMatchers("/**").permitAll()
                    .requestMatchers("/comment/**").authenticated()  // 댓글 기능은 인증 필요
                    .anyRequest().authenticated()
            )
            .formLogin((formLogin) -> 
                formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/mypage", true)
                    .permitAll()
            )
            .csrf(csrf -> 
                csrf
                    .csrfTokenRepository(csrfTokenRepository())
                    .ignoringRequestMatchers("/login",  "/write", "/add", "/comment", "/detail", "/search")
            )
            .logout(logout -> 
                logout.logoutUrl("/logout") 
            ); 
        
        return http.build();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
