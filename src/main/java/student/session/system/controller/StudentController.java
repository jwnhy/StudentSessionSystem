package student.session.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import student.session.basic.SessionException;
import student.session.system.user.Student;

@Controller
public class StudentController extends BasicController
{
    @RequestMapping(value = "/student/{userName}", method = RequestMethod.GET)
    public String student(Model model, @PathVariable String userName,
                          @RequestParam(required = false) String errorInfo, @RequestParam(required = false) String errorType)
    {
        Student student = (Student) userDAO.findByUserName(userName);
        model.addAttribute("sessions", student.getAvailableSession());
        model.addAttribute("studentSessions", student.getStudentSession());
        model.addAttribute("errorInfo", errorInfo);
        model.addAttribute("errorType", errorType);
        return "student";
    }

    @RequestMapping(value = "/student/{userName}/appointSession/{sessionID}", method = RequestMethod.GET)
    public String appointSession(Model model, @PathVariable String userName, @PathVariable Long sessionID)
    {
        Student student = (Student) userDAO.findByUserName(userName);
        try
        {
            student.appointSession(sessionID);
            return "redirect:/student/" + userName;
        }
        catch (SessionException sessionException)
        {
            model.addAttribute("errorType", "SessionError");
            model.addAttribute("errorInfo", sessionException.getMessage());
            return "redirect:/student/" + userName;
        }
    }

    @RequestMapping(value = "/student/{userName}/deleteSession/{sessionID}", method = RequestMethod.GET)
    public String deleteSession(Model model, @PathVariable String userName, @PathVariable Long sessionID)
    {
        Student student = (Student) userDAO.findByUserName(userName);
        try
        {
            student.deleteSession(sessionID);
            return "redirect:/student/" + userName;
        }
        catch (SessionException sessionException)
        {
            model.addAttribute("errorType", "SessionError");
            model.addAttribute("errorInfo", sessionException.getMessage());
            return "redirect:/student/" + userName;
        }

    }
}
