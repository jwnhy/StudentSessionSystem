package student.session.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import student.session.basic.MessageBuffer;
import student.session.basic.StaticVar;
import student.session.system.session.Session;
import student.session.system.session.SessionUser;
import student.session.system.user.Student;
import student.session.system.user.Teacher;
import student.session.system.user.User;
import student.session.system.user.UserType;

@Controller
public class AdminController extends BasicController
{

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model)
    {
        model.addAttribute("users", userDAO.getAllUser());
        model.addAttribute("displayLimit", StaticVar.displayLimit);
        model.addAttribute("cancelLimit", StaticVar.cancelLimit);
        model.addAttribute("violatedLimit", StaticVar.violatedLimit);
        model.addAttribute("studentLimit", StaticVar.studentLimit);
        return "admin";

    }

    @RequestMapping(value = "/admin/changeIdentity/{userName}", method = RequestMethod.POST)
    public String changeIdentity(Model model, String newUserIdentity, @PathVariable String userName)
    {
        User user = userDAO.findByUserName(userName);
        safeChange(userName);
        if (adminCheck(user)) return "redirect:/admin";
        userDAO.changeIdentityByUserName(userName, newUserIdentity);
        return "redirect:/admin";

    }

    private boolean adminCheck(User user)
    {
        if(user.getUserIdentity().equals(UserType.ADMIN))
        {
            int count = 0;
            for(User s:userDAO.getAllUser())
            {
                if(s.getUserIdentity().equals(UserType.ADMIN))
                    count++;
            }
            if(count == 1)
                return true;

        }
        return false;
    }

    @RequestMapping(value = "/admin/deleteUser/{userName}", method = RequestMethod.GET)
    public String deleteUser(Model model, @PathVariable String userName)
    {
        User user = userDAO.findByUserName(userName);
        safeChange(userName);
        if (adminCheck(user)) return "redirect:/admin";
        userDAO.deleteByUserName(userName);
        return "redirect:/admin";
    }

    private void safeChange(String userName)
    {
        User user = userDAO.findByUserName(userName);
        if (user.getUserIdentity().equals(UserType.TEACHER))
        {
            Teacher teacher = (Teacher) user;
            for (Session s : sessionDAO.getAllSession(teacher))
            {
                sessionUserDAO.deleteSessionUser(s);
                sessionDAO.deleteSession(s.getSessionID());
            }
            for (Student s : teacherStudentDAO.getAllStudent(teacher))
                teacherStudentDAO.deleteStudent(teacher, s);
            MessageBuffer.setMessage(user,"System:Your old info may be deleted because identity change, Please check, We're terribly sorry");
        }
        else if (user.getUserIdentity().equals(UserType.STUDENT))
        {
            MessageBuffer.setMessage(user, "System:Your old info may be deleted because identity change, Please check, We're terribly sorry");
            Student student = (Student) user;
            for (Teacher t : teacherStudentDAO.getAllTeacher(student))
                t.deleteStudent(student);
            sessionUserDAO.deleteSessionUser(student);
        }
    }

    @RequestMapping(value = "/admin/changeDisplayLimit", method = RequestMethod.POST)
    public String changeDisplayLimit(Model model, String newDisplayLimit)
    {
        if (!newDisplayLimit.isEmpty() && newDisplayLimit.length() <= 2 && Integer.parseInt(newDisplayLimit)>0)
            StaticVar.displayLimit = Integer.parseInt(newDisplayLimit);
        return "redirect:/admin";
    }
    @RequestMapping(value = "/admin/changeCancelLimit", method = RequestMethod.POST)
    public String changeCancelLimit(Model model, String newCancelLimit)
    {
        if (!newCancelLimit.isEmpty() && newCancelLimit.length() <= 2 && Integer.parseInt(newCancelLimit)>0)
            StaticVar.cancelLimit = Integer.parseInt(newCancelLimit);
        return "redirect:/admin";
    }
    @RequestMapping(value = "/admin/changeViolatedLimit", method = RequestMethod.POST)
    public String changeViolatedLimit(Model model, String newViolatedLimit)
    {
        if (!newViolatedLimit.isEmpty() && newViolatedLimit.length() <= 2&&Integer.parseInt(newViolatedLimit)>0)
            StaticVar.violatedLimit = Integer.parseInt(newViolatedLimit);
        return "redirect:/admin";
    }
    @RequestMapping(value = "/admin/changeStudentLimit", method = RequestMethod.POST)
    public String changeStudentLimit(Model model, String newStudentLimit)
    {
        if (!newStudentLimit.isEmpty() && newStudentLimit.length() <= 2&&Integer.parseInt(newStudentLimit)>0)
            StaticVar.studentLimit = Integer.parseInt(newStudentLimit);
        return "redirect:/admin";
    }
}
