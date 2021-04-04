package com.project.retroboard.service;

import com.project.retroboard.model.MyUserDetails;
import com.project.retroboard.model.User;
import com.project.retroboard.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createUser(String username, String password, String role){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        userRepository.save(user);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));

        user.orElseThrow(() -> new UsernameNotFoundException("Not found "+username));

        return user.map(MyUserDetails:: new).get();


    }
}
