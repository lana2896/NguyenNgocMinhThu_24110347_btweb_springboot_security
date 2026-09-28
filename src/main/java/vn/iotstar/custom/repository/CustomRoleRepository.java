package vn.iotstar.custom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.custom.entity.CustomRole;

public interface CustomRoleRepository extends JpaRepository<CustomRole, Long> {

	Optional<CustomRole> findByName(String name);

}
