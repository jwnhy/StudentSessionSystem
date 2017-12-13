package student.session.system.user;

import student.session.system.form.UserForm;

public class Student extends User
{

	public Student()
	{
		super();
		setUserIdentity(UserType.STUDENT);
		// TODO Auto-generated constructor stub
	}

	public Student(UserForm form)
	{
		super(form);
		setUserIdentity(UserType.STUDENT);
		// TODO Auto-generated constructor stub
	}

}
