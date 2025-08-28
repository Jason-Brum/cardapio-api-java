package br.com.cardapio.cardapio_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desativa a proteção CSRF, comum para APIs REST
            .csrf(csrf -> csrf.disable())
            
            // Define a gestão de sessão como STATELESS (sem estado),
            // o que significa que a API não guarda sessões.
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // Configura as regras de autorização para os pedidos HTTP
            .authorizeHttpRequests(authorize -> authorize
                // Permite o acesso público a qualquer endpoint que comece com "/api/"
                .requestMatchers("/api/**").permitAll()
                // Exige que todos os outros pedidos (que não são da nossa API) sejam autenticados
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
