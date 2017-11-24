package student.session.system.controller;
import student.session.basic.database.UserDAO;
import student.session.system.form.UserForm;
import student.session.system.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class LoginController {
	@Autowired
	@Qualifier("jdbcUserDAO")
	private UserDAO userDAO;
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, UserForm userForm, RedirectAttributes attributes) {
		User user = new User(userForm);
		//ApplicationContext context = new ClassPathXmlApplicationContext("/spring/dataSource.xml");
		//JdbcUserDAO userDAO = (JdbcUserDAO) context.getBean("jdbcUserDAO"); 
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
		else
		{
			return "redirect:/manager";
		}	
		
	}
}
