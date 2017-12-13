package student.session.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import student.session.basic.StaticVar;

@Controller
public class ManagerController extends BasicController
{

	@RequestMapping(value="/manager", method=RequestMethod.GET)
	public String manager(Model model)
	{
		model.addAttribute("users",userDAO.getAllUser());
		model.addAttribute("displayLimit",StaticVar.displayLimit);
		return "manager";
		
	}
	@RequestMapping(value="/manager/changeIdentity/{userName}", method=RequestMethod.POST)
	public String changeIdentity(Model model, String newUserIdentity, @PathVariable String userName)
	{
		userDAO.changeIdentityByUserName(userName, newUserIdentity);
		return "redirect:/manager";
		
	}
	@RequestMapping(value="/manager/deleteUser/{userName}",method=RequestMethod.GET)
	public String deleteUser(Model model, @PathVariable String userName)
	{
		userDAO.deleteByUserName(userName);
		return "redirect:/manager";
	}
	@RequestMapping(value="/manager/changeDisplayLimit",method=RequestMethod.POST)
	public String changeDisplayLimit(Model model, String newDisplayLimit)
	{
		if(!newDisplayLimit.isEmpty()&&newDisplayLimit.length()<=2)
			StaticVar.displayLimit = Integer.parseInt(newDisplayLimit);
		return "redirect:/manager";
	}
}
