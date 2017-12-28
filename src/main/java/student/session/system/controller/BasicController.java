package student.session.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import student.session.basic.database.SessionDAO;
import student.session.basic.database.SessionUserDAO;
import student.session.basic.database.TeacherStudentDAO;
import student.session.basic.database.UserDAO;

public class BasicController
{
    @Autowired
    @Qualifier("jdbcUserDAO")
    protected UserDAO userDAO;

    @Autowired
    @Qualifier("jdbcSessionDAO")
    protected SessionDAO sessionDAO;

    @Autowired
    @Qualifier("jdbcSessionUserDAO")
    protected SessionUserDAO sessionUserDAO;

    @Autowired
    protected TeacherStudentDAO teacherStudentDAO;
}
