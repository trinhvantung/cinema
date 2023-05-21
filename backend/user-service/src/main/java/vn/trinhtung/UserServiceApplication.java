package vn.trinhtung;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.entity.Role;
import vn.trinhtung.entity.User;
import vn.trinhtung.repository.RoleRepository;
import vn.trinhtung.repository.UserRepository;

//@EnableMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(proxyTargetClass = true, prePostEnabled = true)
@EnableKafka
@EnableEurekaClient
@RequiredArgsConstructor
@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.count() == 0) {
			Role admin = new Role(null, "ADMIN");
			Role user = new Role(null, "USER");

			roleRepository.saveAll(Arrays.asList(admin, user));
		}

		if (userRepository.count() == 0) {
			Role adminRole = roleRepository.findByName("ADMIN").get();
			User admin = User.builder().activationCode("").email("tungvlhy@gmail.com").enable(true)
					.nonLock(true).password(passwordEncoder.encode("12345"))
					.fullName("Trịnh Văn Tùng")
					.roles(Collections.singletonList(adminRole)).build();

			Role userRole = roleRepository.findByName("USER").get();
			User user = User.builder().activationCode("").email("tvtung07122001@gmail.com")
					.enable(true).nonLock(true).password(passwordEncoder.encode("12345"))
					.fullName("Trịnh Văn Tùng")
					.roles(Collections.singletonList(userRole)).build();

			userRepository.saveAll(Arrays.asList(admin, user));
		}

		if (userRepository.count() == 2) {
			Role userRole = roleRepository.findByName("USER").get();
			User user = User.builder().activationCode("").email("tung071201@gmail.com")
					.enable(false).nonLock(false).password(passwordEncoder.encode("12345"))
					.fullName("Trịnh Văn Tùng")
					.roles(Collections.singletonList(userRole)).build();

			userRepository.save(user);
		}
	}

}
