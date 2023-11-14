package in.userreg.binding;

import lombok.Data;

@Data
public class UnLockAccount {
	
	private String email;
	private String tempPazzword;
	private String newPazzword;
	private String confirmPazzword;
	

}
