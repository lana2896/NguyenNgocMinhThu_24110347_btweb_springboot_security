package vn.iotstar.custom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import lombok.RequiredArgsConstructor;
import vn.iotstar.custom.repository.CustomUserRepository;
import vn.iotstar.custom.security.CustomLoginUserDetailsService;

@Configuration
@RequiredArgsConstructor
public class CustomSecurityConfig {

	private final CustomUserRepository customUserRepository;
	private final PasswordEncoder passwordEncoder;

	@Bean
	@Order(1)
	SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
		DaoAuthenticationProvider provider =
				new DaoAuthenticationProvider(new CustomLoginUserDetailsService(customUserRepository));
		provider.setPasswordEncoder(passwordEncoder);

		HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();
		contextRepository.setSpringSecurityContextKey("CUSTOM_SECURITY_CONTEXT");

		http
				.securityMatcher("/custom/**")
				.authenticationManager(new ProviderManager(provider))
				.securityContext(context -> context.securityContextRepository(contextRepository))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/custom/login").permitAll()
						.requestMatchers("/custom/admin/**").hasRole("ADMIN")
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.loginPage("/custom/login")
						.loginProcessingUrl("/custom/login")
						.defaultSuccessUrl("/custom/", true)
						.failureUrl("/custom/login?error=true")
						.permitAll()
				)
				.logout(logout -> logout
						.logoutUrl("/custom/logout")
						.logoutSuccessUrl("/custom/login?logout=true")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll()
				);
		return http.build();
	}

}
