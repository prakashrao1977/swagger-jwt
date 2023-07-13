package com.test.microservice.security;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername (String username)
        throws UsernameNotFoundException
    {
        return findUserbyUername(username);
    }

    private User findUserbyUername (String username)
    {
        if (username.equalsIgnoreCase("john@dev.in")) {
            return new User(username, passwordEncoder.encode("xyz"), getAuthorities());
        }
        
        return null;
    }
    
    private List<GrantedAuthority> getAuthorities ()
    {
        List<GrantedAuthority> authorities = new CopyOnWriteArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        
        return authorities;
    }
}
