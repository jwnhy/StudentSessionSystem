package student.session.system.form;
import student.session.system.user.*;
public class UserForm {
	private String userName;
	private String userPassword;
	private String personName;
	private userType userIdentity;
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

	public userType getUserIdentity()
	{
		return userIdentity;
	}

	public void setUserIdentity(userType userIdentity)
	{
		this.userIdentity = userIdentity;
	}
	
	public String getPersonName()
	{
		return personName;
	}
	
	public void setPersonName(String personName)
	{
		this.personName = personName;
	}
}
