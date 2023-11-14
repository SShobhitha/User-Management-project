package in.userreg.service;

import java.util.List;
import java.util.Map;

import in.userreg.binding.LoginForm;
import in.userreg.binding.RegistrationForm;
import in.userreg.binding.UnLockAccount;

public interface UserRegService {
	
	public Map<Long,String> getCountry();
	Map<Long,String> getState(Long countryId);
	List<String> getCities(Long stateId);
	public String signIn(LoginForm loginform);
	public String signUp(RegistrationForm registrationForm);
	public String unlockAccount(UnLockAccount unLockAccount);
	public String forgetPazzword(String email);
	

}
