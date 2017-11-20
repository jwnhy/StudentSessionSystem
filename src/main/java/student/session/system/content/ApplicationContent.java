package student.session.system.content;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import student.session.basic.database.*;
import student.session.system.user.*;

public class ApplicationContent
{
	public static void main(String args[])
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/dataSource.xml");

		JdbcUserDAO userDAO = (JdbcUserDAO) context.getBean("jdbcUserDAO");
		User user = new User();
		user.setUserName("admin");
		user.setUserPassword("admin");
		user.setPersonName("JohnWeston");
		user.setUserIdentity(userType.MANAGER);
		userDAO.insertUser(user);
	}
}
