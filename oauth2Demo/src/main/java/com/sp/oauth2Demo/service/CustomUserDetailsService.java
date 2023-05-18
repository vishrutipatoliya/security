package com.sp.oauth2Demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sp.oauth2Demo.entity.User;
import com.sp.oauth2Demo.repository.UserRepository;
import java.util.List;
import com.sp.oauth2Demo.entity.CustomUserDetails;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("no user");
        }
        return new CustomUserDetails(user);
    }
}
