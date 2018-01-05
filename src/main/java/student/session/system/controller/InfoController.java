package student.session.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import student.session.system.session.Session;
import student.session.system.session.SessionUser;
import student.session.system.user.Student;
import student.session.system.user.Teacher;
import student.session.system.user.User;
import student.session.system.user.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class InfoController extends BasicController
{
    @RequestMapping(value = "/showInfo/{userName}",method = RequestMethod.GET)
    public String userInfo(Model model, @PathVariable String userName)
    {
        User user = userDAO.findByUserName(userName);
        model.addAttribute("user", user);
        model.addAttribute("userType",user.getUserIdentity().toString().toLowerCase());
        if(user.getUserIdentity()== UserType.STUDENT)
        {
            int violatedTimes = 0, userUsedTime = 0, userTimes=0;
            Student student = (Student) user;
            for(Teacher teacher:teacherStudentDAO.getAllTeacher(student))
            {
                violatedTimes += student.getViolatedTimes(teacher);
                userUsedTime += teacherStudentDAO.getUserUsedTime(teacher,student);
                userTimes += teacherStudentDAO.getUserTimes(teacher,student);
            }
            model.addAttribute("violatedTimes",violatedTimes);
            model.addAttribute("userUsedTime",userUsedTime);
            model.addAttribute("userTimes",userTimes);
        }

        return "userInfo";
    }


    @RequestMapping(value = "/teacher/{userName}/appointInfo", method = RequestMethod.POST)
    public String appointInfo(Model model, @PathVariable String userName, String infoStartDate, String infoEndDate)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        ArrayList<SessionUser> sessionUserList = new ArrayList<>();
        for (Session s : sessionDAO.getAllSession(teacher, (Session s) -> s.getSessionDate()
                .isAfter(LocalDate.parse(infoStartDate).minusDays(1)) && s.getSessionDate()
                .isBefore(LocalDate.parse(infoEndDate).plusDays(1))))
        {
            sessionUserList.addAll(sessionUserDAO.getAllSessionUser(s));
        }
        model.addAttribute("sessionUserList", sessionUserList);
        ArrayList<Session> sessionList = new ArrayList<>();
        for (SessionUser sessionUser : sessionUserList)
            sessionList.add(sessionDAO.getSession(sessionUser.getSessionID()));
        model.addAttribute("sessionList", sessionList);
        model.addAttribute("userDAO",userDAO);
        model.addAttribute("presentDate", infoStartDate);
        model.addAttribute("nextMonthDate", infoEndDate);
        return "appointInfo";
    }

    @RequestMapping(value = "/teacher/{userName}/appointInfo", method = RequestMethod.GET)
    public String appointInfo(Model model, @PathVariable String userName)
    {
        LocalDate presentDate = LocalDate.now();
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        model.addAttribute("presentDate", presentDate.format(dateFormatter));
        model.addAttribute("nextMonthDate", presentDate.plusMonths(2).format(dateFormatter));
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        ArrayList<SessionUser> sessionUserList = new ArrayList<>();
        for (Session s : sessionDAO.getAllSession(teacher, (Session s) -> s.getSessionDate()
                .isAfter(presentDate.minusDays(1)) && s.getSessionDate()
                .isBefore(presentDate.plusMonths(2).plusDays(1))))
        {
            sessionUserList.addAll(sessionUserDAO.getAllSessionUser(s));
        }
        model.addAttribute("sessionUserList", sessionUserList);
        ArrayList<Session> sessionList = new ArrayList<>();
        for (SessionUser sessionUser : sessionUserList)
            sessionList.add(sessionDAO.getSession(sessionUser.getSessionID()));
        model.addAttribute("sessionList", sessionList);
        model.addAttribute("userDAO",userDAO);
        return "appointInfo";
    }
}
