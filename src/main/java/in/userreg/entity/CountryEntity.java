package in.userreg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="COUNTRY_DETAILS")
@Data
public class CountryEntity {
	
	
	@Id
	@Column(name="COUNTRY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long countryId;
	
	
	@Column(name="COUNTRY_NAME")
	private String countryName;

}
