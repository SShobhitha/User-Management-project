package in.userreg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.userreg.entity.CountryEntity;


@Repository
public interface CountryRepo extends JpaRepository<CountryEntity, Long> {

}
