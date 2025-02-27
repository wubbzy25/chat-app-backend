package com.chat.backend.Services;

import com.chat.backend.Models.Users;
import com.chat.backend.Repositories.UserRepository;
import com.chat.backend.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersOptional = userRepository.findByEmail(username);
        if (usersOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Users user = usersOptional.get();
        return new CustomUserDetails(user.getEmail());
    }
}