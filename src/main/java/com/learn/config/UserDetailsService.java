//package com.learn.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//
//    private InMemoryUserDetailsManager userDetailsManager;
//    public UserDetailsService() {
//        userDetailsManager = new InMemoryUserDetailsManager();
//        // Add users and their authorities to the InMemoryUserDetailsManager
//        addUserWithAuthorities("user", "password", Collections.singletonList("ROLE_USER"));
//        addUserWithAuthorities("admin", "password", Collections.singletonList("ROLE_ADMIN"));
//    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userDetailsManager.loadUserByUsername(username);
//    }
//    private void addUserWithAuthorities(String username, String password, List<String>roles) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username(username)
//                .password(password)
//                .authorities(authorities)
//                .build();
//        userDetailsManager.createUser(user);
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username(username)
//                .password(password)
//                .authorities(authorities)
//                .build();
//        userDetailsManager.createUser(admin);
//    }
//
//
//
//}
