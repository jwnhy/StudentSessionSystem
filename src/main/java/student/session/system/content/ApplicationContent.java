package student.session.system.content;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import student.session.basic.database.*;
import student.session.system.user.User;

public class ApplicationContent {
	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/dataSource.xml");

		JdbcUserDAO userDAO = (JdbcUserDAO) context.getBean("jdbcUserDAO");
		User user = new User();
		user.setPersonName("sdasdw");
		user.setUserPassword("sdsdw");
		user.setUserName("sdasd");
		userDAO.insertUser(user);

	}
}
