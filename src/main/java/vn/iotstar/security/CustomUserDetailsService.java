package vn.iotstar.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import vn.iotstar.repository.UserRepository;

@Service @RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(user->User.withUsername(user.getUsername()).password(user.getPassword()).roles(user.getRole().getName().replace("ROLE_","")).disabled(!user.isEnabled()).build()).orElseThrow(()->new UsernameNotFoundException("Username không tồn tại"));
    }
}
