package in.userreg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.userreg.binding.LoginForm;
import in.userreg.binding.RegistrationForm;
import in.userreg.binding.UnLockAccount;
import in.userreg.service.UserRegImplService;

@RestController
public class UserRegController {
	
	@Autowired
	private UserRegImplService userRegImplService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		return userRegImplService.signIn(loginForm);
	}
	
	@GetMapping("/forgetpassword/{email}")
	public String forgetPassword(@PathVariable("email") String email) {
		return userRegImplService.forgetPazzword(email);
	}
	
	@PostMapping("/registration")
	public String register(@RequestBody RegistrationForm registrationForm) {
		return userRegImplService.signUp(registrationForm);
	}
	
	@PostMapping("/unlock")
	public String unlockAccount(@RequestBody UnLockAccount unLockAccount) {
		return userRegImplService.unlockAccount(unLockAccount);
	}
	
	

}
