package com.example.photogram;

import com.example.photogram.domain.user.Users;
import com.example.photogram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PhotogramApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PhotogramApplication.class, args);
	}

	@PostConstruct
	public void setUp() {
		Users users = new Users();
		users.setUsername("user");
		users.setName("유저");
		users.setEmail("user@email.com");
		users.setPassword(passwordEncoder.encode("123"));
		userRepository.save(users);
	}

}
