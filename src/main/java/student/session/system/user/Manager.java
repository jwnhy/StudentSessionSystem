package student.session.system.user;

import student.session.system.form.UserForm;

public class Manager extends User
{

	public Manager()
	{
		super();
		setUserIdentity(UserType.MANAGER);
		// TODO Auto-generated constructor stub
	}

	public Manager(UserForm form)
	{
		super(form);
		setUserIdentity(UserType.MANAGER);
		// TODO Auto-generated constructor stub
	}

}
