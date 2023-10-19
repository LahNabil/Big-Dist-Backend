package BigDistV2.group.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import BigDistV2.group.backend.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
	Optional<Roles> findByName(String name);

}
