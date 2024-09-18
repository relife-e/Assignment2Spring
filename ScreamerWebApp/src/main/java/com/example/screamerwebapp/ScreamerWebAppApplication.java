package com.example.screamerwebapp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anmol Saru Magar & Roshan Phakami PunMagar
 * File Name: ServerApplication.java
 * Date :16/9/2024
 * Purpose :
 * Runs ServerApplicaiton
 * ******************************************************
 */
//admin feign client interface
@SpringBootApplication @EnableFeignClients
@EnableDiscoveryClient
public class ScreamerWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScreamerWebAppApplication.class, args);
    }

}


@Configuration // Marks this class as a configuration class for Spring.
@EnableWebSecurity // Enables web security for the application.
 class WebSecurityConfiguration  {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain _filterChain(HttpSecurity http) throws Exception {
        /*
        return http
                .authorizeHttpRequests(request
                        -> request.requestMatchers(HttpMethod.GET, "/view/**").hasAnyRole("User", "Admin")
                        .requestMatchers(HttpMethod.GET, "/admin/customer/view/**").hasAnyRole("Admin")
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .build();
        */
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    
                    auth.requestMatchers("/view/**").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}




@Service
@RequiredArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerClient custClient;
    

    @Override
    public UserDetails loadUserByUsername(final String email) {
        final Customer cust = this.custClient.getByEmail(email);
        
      System.out.println("Customer auth debug: " + cust);
        if (cust == null) {
            System.out.println("Error no user with email: " + email);
        }
        return User.withUsername(cust.getEmail())
                .password(cust.getPassword())
                .authorities(cust.getRoll())// need to add this to databsae
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}








