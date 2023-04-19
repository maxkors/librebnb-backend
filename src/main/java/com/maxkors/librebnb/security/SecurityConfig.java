package com.maxkors.librebnb.security;

import com.maxkors.librebnb.infrastructure.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf()
//                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                    .and()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(configurer -> configurer
                        .requestMatchers(HttpMethod.GET, "/rooms").permitAll()
                        .requestMatchers(HttpMethod.GET, "/rooms/all").hasRole(RoleName.ROLE_ADMIN.value())
                        .requestMatchers(HttpMethod.GET, "/rooms/favourite").authenticated()
                        .requestMatchers("/rooms/{id}/like").authenticated()
                        .requestMatchers(HttpMethod.GET, "/profile").authenticated()
                        .anyRequest().denyAll())
                .formLogin(configurer -> configurer
                        .permitAll()
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler((req, res, auth) -> {
                            res.setStatus(HttpStatus.OK.value());
                            res.sendRedirect("/api/profile");
                        })
                        .failureHandler((req, res, ex) -> res.sendError(HttpStatus.UNAUTHORIZED.value())))
//                .rememberMe(configurer -> configurer
//                        .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
//                        .tokenRepository()
//                        .key("remember_me_secret_key"))
                .logout(configurer -> configurer
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.POST.name()))
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "remember-me")
                        .logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpStatus.UNAUTHORIZED.value())))
                .exceptionHandling(configurer -> configurer
                        .authenticationEntryPoint((req, res, authEx) -> res.sendError(HttpStatus.UNAUTHORIZED.value())))
        ;

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
