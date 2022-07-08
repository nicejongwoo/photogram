package com.example.photogram;

import com.example.photogram.domain.subscribe.Subscribe;
import com.example.photogram.domain.user.Users;
import com.example.photogram.repository.SubscribeRepository;
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

	@Autowired
	private SubscribeRepository subscribeRepository;

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

		Users users2 = new Users();
		users2.setUsername("cos");
		users2.setName("코스");
		users2.setEmail("cos@email.com");
		users2.setPassword(passwordEncoder.encode("123"));
		userRepository.save(users2);

		Users users3 = new Users();
		users3.setUsername("love");
		users3.setName("러브");
		users3.setEmail("love@email.com");
		users3.setPassword(passwordEncoder.encode("123"));
		userRepository.save(users3);

		Subscribe subscribe1 = Subscribe.builder().fromUser(users).toUser(users2).build();
		Subscribe subscribe2 = Subscribe.builder().fromUser(users).toUser(users3).build();
		Subscribe subscribe3 = Subscribe.builder().fromUser(users2).toUser(users).build();
		Subscribe subscribe4 = Subscribe.builder().fromUser(users2).toUser(users3).build();

		subscribeRepository.save(subscribe1);
		subscribeRepository.save(subscribe2);
		subscribeRepository.save(subscribe3);
		subscribeRepository.save(subscribe4);

	}

}
