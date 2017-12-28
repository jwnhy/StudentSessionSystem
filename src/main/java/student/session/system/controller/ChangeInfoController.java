package student.session.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import student.session.basic.InfoException;
import student.session.system.form.UserForm;
import student.session.system.user.User;


@Controller
public class ChangeInfoController extends BasicController
{
    @RequestMapping(value = "/changeInfo/{userName}", method = RequestMethod.GET)
    public String changeInfo(Model model, @PathVariable String userName, @RequestParam(value = "errorType", required = false) String errorType,
                             @RequestParam(value = "errorInfo", required = false) String errorInfo)
    {
        User user = userDAO.findByUserName(userName);
        model.addAttribute("presentUser", user);
        model.addAttribute("errorInfo", errorInfo);
        model.addAttribute("errorType", errorType);
        return "changeInfo";
    }

    @RequestMapping(value = "/changeInfo/{userName}", method = RequestMethod.POST)
    public String changeInfo(Model model, UserForm userForm, @PathVariable String userName)
    {
        User user = userDAO.findByUserName(userName);
        try
        {
            user.alterUser(userForm);
            return "redirect:/changeInfo/" + userName;
        }
        catch (InfoException e)
        {

            model.addAttribute("errorInfo", e.getMessage());
            model.addAttribute("errorType", "infoError");
            return "redirect:/changeInfo/" + userName;
        }

    }
}
