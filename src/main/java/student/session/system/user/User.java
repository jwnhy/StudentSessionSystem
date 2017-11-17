package student.session.system.user;
import student.session.system.form.UserForm;
import student.session.basic.database.*;

import java.util.regex.*;

public class User {
	private String userName;
	private String userPassword;
	private String personName;
	
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public User(UserForm form)
	{
		this.setUserName(form.getUserName());
		this.setUserPassword(form.getUserPassword());
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public boolean isValid() {
		Pattern userNamePattern = Pattern.compile("^\\w{6,10}");
		Pattern userPasswordPattern = Pattern.compile("^\\w{6,16}");
		if(userNamePattern.matcher(userName).matches()==true && 
			userPasswordPattern.matcher(userPassword).matches()==true)
			return true;
		else return false;
	}
}
