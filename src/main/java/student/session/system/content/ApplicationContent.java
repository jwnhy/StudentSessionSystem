package student.session.system.content;

import java.util.Scanner;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import student.session.basic.database.*;
import student.session.system.user.*;

public class ApplicationContent
{
	@Autowired
	UserDAO userDAO; 
	void insert()
	{
		User user = new User();
		user.setUserName("student1");
		user.setUserPassword("student1");
		user.setPersonName("student1");
		user.setUserIdentity(userType.STUDENT);
		userDAO.insertUser(user);
	}
	public static void main(String args[])
	{
		ApplicationContent applicationContent = new ApplicationContent();
		applicationContent.insert();
	}
}
