package student.session.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import student.session.system.session.Session;
import student.session.system.session.SessionUser;
import student.session.system.user.Teacher;
import student.session.system.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class ReportController extends BasicController
{
    @RequestMapping(value = "/teacher/{userName}/lastMonthReport", method = RequestMethod.GET)
    public String teacherReport(Model model, @PathVariable String userName)
    {
        Teacher teacher = (Teacher) userDAO.findByUserName(userName);
        ArrayList<SessionUser> sessionUserList = sessionUserDAO.getAllSessionUser(teacher, (SessionUser sessionUser) -> sessionDAO
                .getSession(sessionUser.getSessionID())
                .getSessionDate()
                .isBefore(LocalDate.now().minusMonths(1)));
        ArrayList<Session> sessionList = sessionDAO.getAllSession(teacher, (Session session) -> session
                .getSessionDate()
                .isBefore(LocalDate.now().minusMonths(1)));
        int violatedTimes = 0, userUsedTime = 0, userTimes = 0;
        for (SessionUser s : sessionUserList)
        {
            violatedTimes += s.getViolatedTimes();
            userUsedTime += s.getUserUsedTime();
            userTimes += s.getUserTimes();
        }
        model.addAttribute("userName", userName);
        model.addAttribute("violatedTimes", violatedTimes);
        model.addAttribute("userUsedTime", userUsedTime);
        model.addAttribute("userTimes", userTimes);
        model.addAttribute("sessionCount", sessionList.size());
        return "lastMonthReport";
    }

}
