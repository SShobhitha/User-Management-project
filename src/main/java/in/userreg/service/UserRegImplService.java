package in.userreg.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.userreg.binding.LoginForm;
import in.userreg.binding.RegistrationForm;
import in.userreg.binding.UnLockAccount;
import in.userreg.entity.CountryEntity;
import in.userreg.entity.StateEntity;
import in.userreg.entity.UserRegEntity;
import in.userreg.repository.CityRepo;
import in.userreg.repository.CountryRepo;
import in.userreg.repository.StateRepo;
import in.userreg.repository.UserRegRepo;
import in.userreg.utils.EmailUtils;

@Service
public class UserRegImplService implements UserRegService {
	
	@Autowired
	private UserRegRepo userRegRepo;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	

	@Override
	public Map<Long, String> getCountry() {
		List<CountryEntity> countrylist = countryRepo.findAll();
		return countrylist.stream().collect(Collectors.toMap(CountryEntity::getCountryId,CountryEntity::getCountryName));
	}

	@Override
	public Map<Long, String> getState(Long countryId) {
		List<StateEntity> statelist = stateRepo.findByCountryId(countryId);
		return statelist.stream().collect(Collectors.toMap(StateEntity::getStateId, StateEntity::getStateName));
	}

	@Override
	public List<String> getCities(Long stateId) {
		 return cityRepo.findByStateId(stateId).stream().map(e->e.getCityName()).collect(Collectors.toList());
	}
	
	@Override
	public String signIn(LoginForm loginform) {
		UserRegEntity userlist = userRegRepo.findByEmail(loginform.getEmail());
		if(userlist!=null && userlist.getPazzword()==loginform.getPazzword() && "UNLOCKED".equals(userlist.getStatus()))
			return "Welcome to Ashok It";
		if(userlist.getPazzword()!=loginform.getPazzword())
		    return "Invalid credentials";
		return "Account is locked";
	}
	
	@Override
	public String signUp(RegistrationForm registrationForm) {
		UserRegEntity userRegEntity=new UserRegEntity();
		UserRegEntity regdetailes = userRegRepo.findByEmail(registrationForm.getEmail());
		if(regdetailes!=null)
			return "The account is already exists with this email id";
		BeanUtils.copyProperties(registrationForm,userRegEntity);
		userRegEntity.setPazzword(generateRandompzz(7));
		userRegEntity.setStatus("LOCKED");
		userRegRepo.save(userRegEntity);
		String fileName="PASSWORD-FOR-NEW-ACCOUNT-EMAIL-BODY-TEMPLATE_CW.txt";
	    String body  = readMailBodyContent(fileName, userRegEntity);
		emailUtils.sendEmail(userRegEntity.getEmail(), "Unlock IES account",body);
		return "Please chcek your email to unlock the account" ;
	}
	
	@Override
	public String unlockAccount(UnLockAccount unLockAccount) {
		UserRegEntity userRegEntity=new UserRegEntity();
		userRegEntity=userRegRepo.findByEmail(unLockAccount.getEmail());
		if(userRegEntity==null)
			return "the account doesn't exist";
		if((unLockAccount.getConfirmPazzword()).equals((unLockAccount.getNewPazzword()))) {
			if(unLockAccount.getTempPazzword().equals(userRegEntity.getPazzword())) {
				userRegEntity.setPazzword(unLockAccount.getNewPazzword());
				userRegEntity.setStatus("UNLOCKED");
				userRegRepo.save(userRegEntity);
				
			}
			else {
				return "Please enter the password mentioned in the email";
			}
		}
			else { 
				return "New password and confirm should be same";
			}
		
			
		
		
		return "The password has been changed";
	}
	
	@Override
	public String forgetPazzword(String email) {
		UserRegEntity userRegEntity=new UserRegEntity();
		userRegEntity= userRegRepo.findByEmail(email);
		if(userRegEntity==null)
			return "Email Id doesn't exists";
		else {
			String fileName="PASSWORD-FOR-NEW-ACCOUNT-EMAIL-BODY-TEMPLATE_CW.txt";
		    String body  = readMailBodyContent(fileName, userRegEntity);
			emailUtils.sendEmail(email, "Password Reset", body);
		}
		return "Please check your email for password";
	}
	
	public String generateRandompzz(int n) {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = n;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	   return generatedString;
		
	}
	private String readMailBodyContent(String filename, UserRegEntity entity) {
		String mailBody="";
		try(BufferedReader br= new BufferedReader(new FileReader(filename))) {
		StringBuilder sb= new StringBuilder();
		String line=br.readLine();
		 
		while(line!=null) {
			sb.append(line);
			line=br.readLine();
		}
			mailBody=sb.toString();
			mailBody=mailBody.replace("{FIRST_NAME}", entity.getFirstName());
			mailBody=mailBody.replace("{LAST_NAME}", entity.getLastName());
			mailBody=mailBody.replace("{PASSWORD}", entity.getPazzword());
			
		}	
	catch(Exception e){
		e.printStackTrace();
		}
		return mailBody;
	}

}
