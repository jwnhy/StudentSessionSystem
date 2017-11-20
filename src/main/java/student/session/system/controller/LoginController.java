package student.session.system.controller;
import student.session.basic.database.JdbcUserDAO;
import student.session.basic.database.UserDAO;
import student.session.system.form.UserForm;
import student.session.system.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class LoginController {
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, UserForm userForm) {
		User user = new User(userForm);
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/dataSource.xml");
		JdbcUserDAO userDAO = (JdbcUserDAO) context.getBean("jdbcUserDAO"); 
		User user = 
		if(user.isValid()!=true)
		{
			model.addAttribute("isValid", false);
			return "home";
		}
		
		if(userDAO.findByUserName(user.getUserName())==null)
		{
			model.addAttribute("isExist", false);
			return "home";
		}
		if(!userDAO.findByUserName(user.getUserName()).getUserPassword().equals(user.getUserPassword()))
		{
			model.addAttribute("isWrong", true);
			return "home";
		}
		
		if()
		
	}
}
