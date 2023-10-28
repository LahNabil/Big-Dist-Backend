package BigDistV2.group.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BigDistV2.group.backend.model.Batterie;

@Repository
public interface BatterieRepository extends JpaRepository<Batterie, Integer> {
	List<Batterie> findByReference(String reference);

}
