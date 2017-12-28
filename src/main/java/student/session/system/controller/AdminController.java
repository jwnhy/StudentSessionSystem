package student.session.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import student.session.basic.StaticVar;
import student.session.system.session.Session;
import student.session.system.user.Student;
import student.session.system.user.Teacher;
import student.session.system.user.UserType;

@Controller
public class AdminController extends BasicController
{

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model)
    {
        model.addAttribute("users", userDAO.getAllUser());
        model.addAttribute("displayLimit", StaticVar.displayLimit);
        return "admin";

    }

    @RequestMapping(value = "/admin/changeIdentity/{userName}", method = RequestMethod.POST)
    public String changeIdentity(Model model, String newUserIdentity, @PathVariable String userName)
    {
        safeChange(userName);
        userDAO.changeIdentityByUserName(userName, newUserIdentity);
        return "redirect:/admin";

    }

    @RequestMapping(value = "/admin/deleteUser/{userName}", method = RequestMethod.GET)
    public String deleteUser(Model model, @PathVariable String userName)
    {
        safeChange(userName);
        userDAO.deleteByUserName(userName);
        return "redirect:/admin";
    }

    private void safeChange(String userName)
    {
        if (userDAO.findByUserName(userName).getUserIdentity().equals(UserType.TEACHER))
        {
            for (Session s : sessionDAO.getAllSession(userDAO.findByUserName(userName)))
            {
                sessionDAO.deleteSession(s.getUser(), s);
            }
        }
        else if (userDAO.findByUserName(userName).getUserIdentity().equals(UserType.STUDENT))
        {
            Student s = (Student) userDAO.findByUserName(userName);
            for (Teacher teacher : teacherStudentDAO.getAllTeacher(s))
            {
                teacher.deleteStudent(s);
            }
        }
    }

    @RequestMapping(value = "/admin/changeDisplayLimit", method = RequestMethod.POST)
    public String changeDisplayLimit(Model model, String newDisplayLimit)
    {
        if (!newDisplayLimit.isEmpty() && newDisplayLimit.length() <= 2)
            StaticVar.displayLimit = Integer.parseInt(newDisplayLimit);
        return "redirect:/admin";
    }
}
