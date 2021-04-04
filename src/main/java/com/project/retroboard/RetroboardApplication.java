package com.project.retroboard;

import com.project.retroboard.repo.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class RetroboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetroboardApplication.class, args);
	}

}
