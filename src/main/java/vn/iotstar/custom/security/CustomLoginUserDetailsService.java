package vn.iotstar.custom.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;
import vn.iotstar.custom.entity.CustomUser;
import vn.iotstar.custom.repository.CustomUserRepository;

@RequiredArgsConstructor
public class CustomLoginUserDetailsService implements UserDetailsService {

	private final CustomUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		CustomUser user = userRepository
				.findByUsernameOrEmail(login, login)
				.orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy username/email: " + login));
		return new CustomUserDetails(
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				user.getPassword(),
				user.getFullName(),
				user.getImages(),
				user.getRole().getName(),
				user.isEnabled());
	}

}
