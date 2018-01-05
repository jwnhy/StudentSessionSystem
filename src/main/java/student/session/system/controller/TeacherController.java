package student.session.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import student.session.basic.MessageBuffer;
import student.session.basic.SessionException;
import student.session.basic.TeacherStudentException;
import student.session.system.form.MultiSessionForm;
import student.session.system.form.SessionForm;
import student.session.system.session.Session;
import student.session.system.user.Student;
import student.session.system.user.Teacher;
import student.session.system.user.User;
import student.session.system.user.UserType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class TeacherController extends BasicController
{

    @RequestMapping(value = "/teacher/{userName}", method = RequestMethod.GET)
    public String teacher(Model model, @RequestParam(value = "errorType", required = false) String errorType,
                          @RequestParam(value = "errorInfo", required = false) String errorInfo, @PathVariable String userName)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        LocalDateTime presentDate = LocalDateTime.now();
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        model.addAttribute("errorInfo", errorInfo);
        model.addAttribute("errorType", errorType);
        model.addAttribute("presentDate", presentDate.format(dateFormatter));
        model.addAttribute("nextMonthDate", presentDate.plusMonths(2).format(dateFormatter));
        model.addAttribute("sessions", sessionDAO.getAllSession(userDAO.findByUserName(userName), (Session temp) ->
                temp.getSessionDate().isAfter(LocalDate.now()) || temp.getSessionDate().equals(LocalDate.now())));
        return "teacher";
    }

    @RequestMapping(value = "/teacher/{userName}/insertSession", method = RequestMethod.POST)
    public String insertSession(Model model, SessionForm sessionForm, @PathVariable String userName)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
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

    @RequestMapping(value = "/teacher/{userName}/insertMultiSession", method = RequestMethod.POST)
    public String insertMultiSession(Model model, MultiSessionForm sessionForm, @PathVariable String userName)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        LocalDate startDate = LocalDate.parse(sessionForm.getSessionStartDate());
        LocalDate endDate = LocalDate.parse(sessionForm.getSessionEndDate());
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(sessionForm.getDayOfWeek());
        while (startDate.isBefore(endDate))
        {
            Session session = null;
            if (startDate.getDayOfWeek().equals(dayOfWeek))
            {
                session = new Session();
                session.setSessionDate(startDate);
                session.setSessionStartTime(LocalTime.parse(sessionForm.getSessionStartTime()));
                session.setSessionEndTime(LocalTime.parse(sessionForm.getSessionEndTime()));
                session.setSessionAddress(sessionForm.getSessionAddress());
                session.setTimesLimit(sessionForm.getTimesLimit());
                session.setTotalTimeLimit(sessionForm.getTotalTimeLimit());
                session.setUser(teacher);
                try
                {
                    teacher.insertSession(session);
                }
                catch (SessionException e)
                {
                    model.addAttribute("errorInfo", e.getMessage());
                    model.addAttribute("errorType", "deleteError");
                    return "redirect:/teacher/" + userName;
                }
            }
            startDate = startDate.plusDays(1);
        }
        return "redirect:/teacher/" + userName;

    }

    @RequestMapping(value = "/teacher/{userName}/deleteSession/{sessionID}", method = RequestMethod.GET)
    public String deleteSession(Model model, @PathVariable Long sessionID, @PathVariable String userName)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        try
        {
            teacher.deleteSession(sessionID);
        }
        catch (SessionException e)
        {
            model.addAttribute("errorInfo", e.getMessage());
            model.addAttribute("errorType", "deleteError");
            return "redirect:/teacher/" + userName;
        }
        return "redirect:/teacher/" + userName;
    }
    @RequestMapping(value = "/teacher/{userName}/deleteMultiSession", method = RequestMethod.POST)
    public String deleteMultiSession(Model model, String[] sessionList, @PathVariable String userName)
    {
        Teacher teacher =(Teacher) userDAO.findByUserName(userName);
        if(sessionList==null)
        {
            model.addAttribute("errorInfo", "No session selected");
            model.addAttribute("errorType", "deleteError");
            return "redirect:/teacher/" + userName;
        }

        try
        {
            for(String s:sessionList)
                teacher.deleteSession(Long.valueOf(s));
        }
        catch (SessionException e)
        {
            model.addAttribute("errorInfo", e.getMessage());
            model.addAttribute("errorType", "deleteError");
            return "redirect:/teacher/" + userName;
        }
        return "redirect:/teacher/" + userName;
    }
    @RequestMapping(value = "/teacher/{userName}/editSession/{sessionID}", method = RequestMethod.GET)
    public String editSession(Model model, @PathVariable String userName, @PathVariable Long sessionID,
                              @RequestParam(required = false) String errorInfo,
                              @RequestParam(required = false) String errorType)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        LocalDateTime presentDate = LocalDateTime.now();
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        model.addAttribute("session", sessionDAO.getSession(sessionID));
        model.addAttribute("userName", userName);
        model.addAttribute("presentDate", presentDate.format(dateFormatter));
        model.addAttribute("nextMonthDate", presentDate.plusMonths(2).format(dateFormatter));
        model.addAttribute("studentList", userDAO.getAllUser((User i) ->
                i.getUserIdentity() == UserType.STUDENT));
        model.addAttribute("teacherStudentList", teacher.getAllStudent());
        model.addAttribute("errorInfo", errorInfo);
        model.addAttribute("errorType", errorType);
        return "editSession";
    }

    @RequestMapping(value = "/teacher/{userName}/editSession/{sessionID}", method = RequestMethod.POST)
    public String editSession(Model model, SessionForm sessionForm, @PathVariable String userName,
                              @PathVariable Long sessionID)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        teacher.replaceSession(sessionID, new Session(sessionForm));
        return "redirect:/teacher/" + userName;
    }
    @RequestMapping(value = "/teacher/{userName}/studentManage")
    public String studentManage(Model model, @PathVariable String userName,
                                @RequestParam(required = false) String errorInfo,
                                @RequestParam(required = false) String errorType)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        model.addAttribute("studentList", userDAO.getAllUser((User user)->user.getUserIdentity().equals(UserType.STUDENT)));
        model.addAttribute("teacherStudentList",teacherStudentDAO.getAllStudent(teacher));
        model.addAttribute("errorInfo", errorInfo);
        model.addAttribute("errorType", errorType);
        return "studentManage";
    }

    @RequestMapping(value = "/teacher/{userName}/addStudent", method = RequestMethod.POST)
    public String addStudent(Model model, String[] studentNameList, @PathVariable String userName)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        if (studentNameList == null)
        {
            model.addAttribute("errorInfo", "Please check at least a student");
            model.addAttribute("errorType", "InsertError");
            return "redirect:/teacher/" + userName + "/studentManage";
        }
        try
        {
            for (String s : studentNameList)
                teacher.addStudent((Student) userDAO.findByPersonName(s));
        }
        catch (TeacherStudentException e)
        {
            model.addAttribute("errorInfo", e.getMessage());
            model.addAttribute("errorType", "ExistError");
        }
        return "redirect:/teacher/" + userName + "/studentManage";
    }
    @RequestMapping(value = "/teacher/{userName}/deleteMultiStudent", method = RequestMethod.POST)
    public String deleteMultiStudent(Model model, String[] teacherStudentNameList, @PathVariable String userName)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        if(teacherStudentNameList == null)
        {
            model.addAttribute("errorInfo", "Please check at least a student");
            model.addAttribute("errorType", "InsertError");
            return "redirect:/teacher/" + userName + "/studentManage";
        }
        for (String s : teacherStudentNameList)
            teacher.deleteStudent((Student) userDAO.findByPersonName(s));

        return "redirect:/teacher/" + userName + "/studentManage";
    }
    @RequestMapping(value = "/teacher/{userName}/addViolatedTimes/{studentName}")
    public String addViolatedTimes(Model model, @PathVariable String userName, @PathVariable String studentName)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        teacher.addViolatedTimes((Student)userDAO.findByUserName(studentName));
        return "redirect:/teacher/" + userName + "/studentManage";
    }
}
