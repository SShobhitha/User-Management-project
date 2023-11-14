package in.userreg.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.userreg.entity.UserRegEntity;

@Repository
public interface UserRegRepo extends JpaRepository<UserRegEntity, Long>{
	
	UserRegEntity findByEmail(String email);
	

}
