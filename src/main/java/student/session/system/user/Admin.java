package student.session.system.user;

import student.session.basic.database.SessionDAO;
import student.session.basic.database.SessionUserDAO;
import student.session.basic.database.TeacherStudentDAO;
import student.session.basic.database.UserDAO;
import student.session.system.form.UserForm;

public class Admin extends User
{

    public Admin()
    {
        super();
        setUserIdentity(UserType.ADMIN);
        // TODO Auto-generated constructor stub
    }

    public Admin(UserForm form)
    {
        super(form);
        setUserIdentity(UserType.ADMIN);
        // TODO Auto-generated constructor stub
    }

    public Admin(UserDAO userDAO, SessionDAO sessionDAO, SessionUserDAO sessionUserDAO, TeacherStudentDAO teacherStudentDAO)
    {
        super(userDAO, sessionDAO, sessionUserDAO, teacherStudentDAO);
    }
}
