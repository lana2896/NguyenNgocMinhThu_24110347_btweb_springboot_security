package vn.iotstar.custom.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import vn.iotstar.custom.entity.CustomRole;
import vn.iotstar.custom.entity.CustomUser;
import vn.iotstar.custom.repository.CustomRoleRepository;
import vn.iotstar.custom.repository.CustomUserRepository;

@Configuration
public class CustomDataInitializer {

	@Bean
	CommandLineRunner initCustomData(
			CustomRoleRepository roleRepository,
			CustomUserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			CustomRole userRole = roleRepository
					.findByName("ROLE_USER")
					.orElseGet(() -> roleRepository.save(
							CustomRole.builder()
									.name("ROLE_USER")
									.build()));

			if (userRepository.findByUsername("user01").isEmpty()) {
				CustomUser user = CustomUser.builder()
						.username("user01")
						.email("user01@gmail.com")
						.password(passwordEncoder.encode("123456"))
						.fullName("Nguyễn Hữu Trung")
						.images("/images/user.png")
						.role(userRole)
						.enabled(true)
						.build();
				userRepository.save(user);
			}
		};
	}

}
