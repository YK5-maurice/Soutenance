package com.YK5maurice.Inventory_management.Config;

import com.YK5maurice.Inventory_management.Services.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class ConfigurationSecurity {

    // UsersService Fournit des utilisateurs pour l'authentification
    private final UsersService usersService;
    private final JwtUtil jwtUtil;

    public ConfigurationSecurity(UsersService usersService, JwtUtil jwtUtil) {
        this.usersService = usersService;
        this.jwtUtil = jwtUtil;
    }

    //SecurityFilterChain : Définit les règles de sécurité pour les requêtes HTTP.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                 //CSRF signifie Cross-Site Request Forgery / falsification de requête intersites
                 .csrf(csrf->csrf.disable()) // Désactive CSRF pour les API stateless
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Pas de session nécessaire
                 .authorizeHttpRequests(auth -> auth
                        // .requestMatchers("/users/create").hasAnyAuthority("ROLE_UTILISATEUR")
                         .requestMatchers("/users/login").permitAll() // Route login accessible sans authentification//
                         //.requestMatchers("/users/users").hasAnyAuthority("ROLE_ADMIN")
                        // .requestMatchers("users/usersliste").hasAnyAuthority("ROLE_UTILISATEUR")
                        .anyRequest().authenticated() // Toutes les autres routes nécessitent une authentification
                ).httpBasic(Customizer.withDefaults())
                .addFilterBefore(new JwtRequestFilter( jwtUtil,usersService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}