package student.session.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import student.session.system.form.UserForm;
import student.session.system.user.User;

@Controller
public class SignUpController extends BasicController
{


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup()
    {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(Model model, UserForm userForm)
    {
        User user = new User(userForm);
        if (!user.isValid())
        {
            model.addAttribute("isValid", false);
            return "signup";
        }
        userDAO.insertUser(user);
        return "redirect:/";

    }
}
