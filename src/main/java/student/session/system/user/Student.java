package student.session.system.user;

import student.session.basic.SessionException;
import student.session.basic.StaticVar;
import student.session.basic.database.SessionDAO;
import student.session.basic.database.SessionUserDAO;
import student.session.basic.database.TeacherStudentDAO;
import student.session.basic.database.UserDAO;
import student.session.system.form.UserForm;
import student.session.system.session.Session;
import student.session.system.session.SessionUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Student extends User
{

    public Student()
    {
        super();
        setUserIdentity(UserType.STUDENT);
        // TODO Auto-generated constructor stub
    }

    public Student(UserForm form)
    {
        super(form);
        setUserIdentity(UserType.STUDENT);
        // TODO Auto-generated constructor stub
    }

    public Student(UserDAO userDAO, SessionDAO sessionDAO, SessionUserDAO sessionUserDAO, TeacherStudentDAO teacherStudentDAO)
    {
        super(userDAO, sessionDAO, sessionUserDAO, teacherStudentDAO);
    }

    public ArrayList<Session> getAvailableSession()
    {
        ArrayList<Teacher> teacherList = teacherStudentDAO.getAllTeacher(this);
        ArrayList<Session> resList = new ArrayList<>();
        for (Teacher t : teacherList)
            resList.addAll(sessionDAO.getAllSession(t, (Session s) -> ((s.getSessionDate().isAfter(LocalDate.now())
                    && s.getSessionDate().isBefore(LocalDate.now().plusDays(StaticVar.displayLimit)))
                    || s.getSessionDate().isEqual(LocalDate.now()))));
        return resList;
    }

    public void appointSession(Long sessionID) throws SessionException
    {
        Session s = sessionDAO.getSession(sessionID);
        if (teacherStudentDAO.getUserTimes((Teacher) s.getUser(), this) > s.getTimesLimit())
            throw new SessionException("Already Over Times Limits");
        else if (teacherStudentDAO.getUserUsedTime((Teacher) s.getUser(), this) > s.getTotalTimeLimit())
            throw new SessionException("Already Over Total Time Limit");
        else if(teacherStudentDAO.getViolatedTimes((Teacher)s.getUser(),this)>StaticVar.violatedLimit)
            throw new SessionException("Over Violated Limits");
        else if(sessionUserDAO.getAllSessionUser(sessionDAO.getSession(sessionID)).size()+1>StaticVar.studentLimit)
            throw new SessionException("The Session is full");
        for (Session temp : getStudentSession())
        {
            if (temp.getSessionID().equals(s.getSessionID()))
                throw new SessionException("Already Appointed");
        }
        sessionUserDAO.insertSessionUser(this, s);
        teacherStudentDAO.incUserTimes((Teacher) s.getUser(), this,1);
        teacherStudentDAO.incUserUsedTime((Teacher) s.getUser(), this, s.getTimeLength().getHour());


    }

    public ArrayList<Session> getStudentSession()
    {
        ArrayList<Session> sessionRes = new ArrayList<>();
        for (SessionUser s : sessionUserDAO.getAllSessionUser(this,(SessionUser temp)->(
                sessionDAO
                        .getSession(temp.getSessionID())
                        .getSessionDate()
                        .isAfter(LocalDate.now())||
                sessionDAO
                        .getSession(temp.getSessionID())
                        .getSessionDate()
                        .isEqual(LocalDate.now())) &&
                sessionDAO
                        .getSession(temp.getSessionID())
                        .getSessionStartTime()
                        .isBefore(LocalTime.now())))
            sessionRes.add(sessionDAO.getSession(s.getSessionID()));
        return sessionRes;
    }

    public void deleteSession(Long sessionID) throws SessionException
    {
        Session s = sessionDAO.getSession(sessionID);
        if (LocalDate.now().plusDays(StaticVar.cancelLimit).isAfter(s.getSessionDate()))
            throw new SessionException("Too Late To Cancel");
        teacherStudentDAO.incUserTimes((Teacher) s.getUser(), this,-1);
        teacherStudentDAO.incUserUsedTime((Teacher) s.getUser(), this, -s.getTimeLength().getHour());
        sessionUserDAO.deleteSessionUser(this, s);

    }
    public int getViolatedTimes(Teacher teacher)
    {
        return teacherStudentDAO.getViolatedTimes(teacher,this);
    }
    public int getViolatedTimes(String teacherName)
    {
        return teacherStudentDAO.getViolatedTimes((Teacher)userDAO.findByUserName(teacherName),this);
    }
}
