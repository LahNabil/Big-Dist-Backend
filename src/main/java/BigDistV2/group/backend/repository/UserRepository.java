package BigDistV2.group.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import BigDistV2.group.backend.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	Optional<UserEntity> findByUsername(String username);
	Boolean existsByUsername(String username);

}
