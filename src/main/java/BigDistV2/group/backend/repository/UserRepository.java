package BigDistV2.group.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import BigDistV2.group.backend.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);

}
