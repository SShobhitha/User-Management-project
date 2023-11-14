package in.userreg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="STATE_DETAILS")
@Data
public class StateEntity {
	
	
	@Id
	@Column(name="STATE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stateId;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@Column(name="COUNTRY_ID")
	private Long countryId;

}
