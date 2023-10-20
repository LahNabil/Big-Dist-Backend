package BigDistV2.group.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import BigDistV2.group.backend.model.OurUser;

public interface OurUserRepository extends JpaRepository<OurUser, Integer> {
	@Query(value = "select * from ourusers where email = ?1", nativeQuery = true)
	Optional<OurUser> findByEmail(String email);

}
