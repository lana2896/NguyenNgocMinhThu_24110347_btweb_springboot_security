package vn.iotstar.custom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.custom.entity.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

	Optional<CustomUser> findByUsername(String username);

	Optional<CustomUser> findByEmail(String email);

	Optional<CustomUser> findByUsernameOrEmail(String username, String email);

}
