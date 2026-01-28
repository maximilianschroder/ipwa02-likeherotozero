package com.iu.ipwa.herotozero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/h2-console/**").permitAll() // Startseite & DB für alle offen
                        .anyRequest().authenticated() // Alles andere (also /admin) braucht Login
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/admin/dashboard", true) // Nach Login direkt zum Dashboard
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/") // Nach Logout zur Startseite
                        .permitAll()
                );

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // fester Nutzer für die Wissenschaftler
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("science")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}