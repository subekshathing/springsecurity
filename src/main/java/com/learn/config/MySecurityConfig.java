package com.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class MySecurityConfig{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((f) ->
                        f.loginPage("/login")
                                .defaultSuccessUrl("/users/listUser")
                                .successHandler((request, response, authentication) -> {
                                    for (GrantedAuthority auth : authentication.getAuthorities()) {
                                        if (auth.getAuthority().equals("ROLE_ADMIN")) {
                                            response.sendRedirect("/users/listUser");
                                        } else if (auth.getAuthority().equals("ROLE_USER")) {
                                            response.sendRedirect("/userPage");
                                        }
                                    }
                                })
                                .permitAll()
                )
                .logout((logout) -> logout.permitAll()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        Collection<SimpleGrantedAuthority> userAuthorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER")
        );
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .authorities(userAuthorities)
                .build();

        Collection<SimpleGrantedAuthority> adminAuthorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN")
        );
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN","USER")
                .authorities(adminAuthorities)
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin((f)->
//                        f.loginPage("/login")
//                                .defaultSuccessUrl("/users/listUser").permitAll())
//                .logout((logout) -> logout.permitAll());
//        return http.build();
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//                Collection<SimpleGrantedAuthority> authorities = Arrays.asList(
//                new SimpleGrantedAuthority("ROLE_ADMIN"),
//                new SimpleGrantedAuthority("ROLE_USER")
//        );
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .authorities(authorities)
//                        .build();
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("password")
//                        .authorities(authorities)
//                        .build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }

}
