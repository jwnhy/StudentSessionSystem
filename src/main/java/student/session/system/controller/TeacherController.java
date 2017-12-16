package student.session.system.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import student.session.basic.SessionException;
import student.session.system.form.SessionForm;
import student.session.system.session.Session;
import student.session.system.user.Teacher;

@Controller
public class TeacherController extends BasicController
{

	@RequestMapping(value = "/teacher/{userName}", method = RequestMethod.GET)
	public String teacher(Model model, @RequestParam(value = "errorType", required = false) String errorType,
			@RequestParam(value = "errorInfo", required = false) String errorInfo, @PathVariable String userName)
	{
		LocalDateTime presentDate = LocalDateTime.now();
		final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
		model.addAttribute("errorInfo", errorInfo);
		model.addAttribute("errorType", errorType);
		model.addAttribute("presentDate", presentDate.format(dateFormatter));
		model.addAttribute("sessions",sessionDAO.getAllSession(userDAO.findByUserName(userName)));
		return "teacher";
	}

	@RequestMapping(value = "/teacher/{userName}/insertSession", method = RequestMethod.POST)
	public String insertSession(Model model, SessionForm sessionForm, @PathVariable String userName)
	{
		Teacher teacher = (Teacher) userDAO.findByUserName(userName);
		teacher.setSessionDAO(sessionDAO);
		try
		{
			teacher.insertSession(new Session(sessionForm));
		}
		catch (SessionException e)
		{

			model.addAttribute("errorInfo", e.getMessage());
			model.addAttribute("errorType", "insertError");
			return "redirect:/teacher/" + userName;
		}
		return "redirect:/teacher/" + userName;
	}
}
