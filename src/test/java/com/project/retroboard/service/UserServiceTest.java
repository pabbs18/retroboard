/*
package com.project.retroboard.service;

import com.project.retroboard.model.User;
import com.project.retroboard.repo.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    //@Autowired
    private UserRepository userRepository;

  */
/*  @Before
    void init(){
        userService = new UserService(userRepository);
    }*//*


    @Test
    public void loadByUsername() {
        //PREPARATION/STUB
        User user = new User();
        user.setUsername("user1");
        user.setPassword("pass");
        user.setRole("Admin");
        Mockito.when(userRepository.findByUsername("user1")).thenReturn(user);

        //ACTION
        UserDetails userDetails = userService.loadByUsername("user1");

        //ASSERTIONS
        verify(userRepository, times(1)).findByUsername("user1");
        assertEquals("pass", userDetails.getPassword());
    }

    @Test
   public void saveUser() {
        //PREPARATION/STUB
        User user = new User();
        user.setUsername("user1");
        user.setPassword("pass");
        user.setRole("Admin");


        //ACTION
        User savedUser = userService.saveUser(user);

        //ASSERTIONS
        assertEquals(savedUser, user);
        assertThat(savedUser).isNotNull();


    }
}*/
