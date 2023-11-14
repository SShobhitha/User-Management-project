package in.userreg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.userreg.entity.StateEntity;
import java.util.List;



@Repository
public interface StateRepo extends JpaRepository<StateEntity, Long> {
	
	List<StateEntity> findByCountryId(Long countryId);

}
