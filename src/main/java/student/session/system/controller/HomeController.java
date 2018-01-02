package student.session.system.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
public class HomeController extends BasicController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home()
    {
        if(LocalDate.now().getDayOfMonth()==30)
            teacherStudentDAO.monthlyClean();
        return "home";
    }

}
