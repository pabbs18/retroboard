package com.project.retroboard.repo;

import com.project.retroboard.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepoTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_HappyPath_ShouldReturn1User() throws Exception{
        //given
        User user = new User();
        user.setUsername("Abe");
        user.setPassword("linc123");
        user.setRole("USER");
        testEntityManager.persist(user);
        testEntityManager.flush();

        //when
        User persistedUser = userRepository.findByUsername("Abe");

        //then
        assertThat(persistedUser).isEqualTo(user);
    }
    @Test
    public void save_HappyPath_ShouldSave1User() throws Exception{
        //given
        User user = new User();
        user.setUsername("Abe");
        user.setPassword("linc123");
        user.setRole("USER");

        //when
        User savedUser = userRepository.save(user);

        //then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();


    }

}
