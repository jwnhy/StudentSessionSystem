package student.session.system.controller;
import student.session.system.form.UserForm;
import student.session.system.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, UserForm userForm) {
		User user = new User(userForm);
		if(user.isValid()!=true)
		{
			model.addAttribute("isValid", false);
			return "home";
		}
		return "manager";
		
	}
}
