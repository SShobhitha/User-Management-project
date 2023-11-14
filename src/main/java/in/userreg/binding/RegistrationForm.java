package in.userreg.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RegistrationForm {
	
	public String firstName;
	public String lastName;
	public String email;
	public Long phno;
	public LocalDate dob;
	public String gender;
	public String country;
	public String states;
	public String city;
	

}
