package student.session.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import student.session.basic.MessageBuffer;
import student.session.system.user.User;

@Controller
public class MessageController extends BasicController
{
    @RequestMapping(value = "/showMessage/{userName}", method = RequestMethod.GET)
    public String showMessage(Model model, @PathVariable String userName)
    {
        User user = userDAO.findByUserName(userName);
        model.addAttribute("msgQueue", MessageBuffer.getMessage(user));
        return "message";
    }
    @RequestMapping(value = "/sendMessage/{userName}", method = RequestMethod.POST)
    public String sendMessage(@PathVariable String userName,String message)
    {
        User user = userDAO.findByUserName(userName);
        MessageBuffer.setMessage(user,message);
        return "redirect:/showInfo/"+userName;
    }
}
