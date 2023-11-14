package in.userreg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.userreg.entity.CityDetails;
import java.util.List;



@Repository
public interface CityRepo extends JpaRepository<CityDetails, Long> {
	
	List<CityDetails> findByStateId(Long stateId);

}
