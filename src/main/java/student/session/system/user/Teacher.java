package student.session.system.user;

import student.session.basic.SessionException;
import student.session.basic.TeacherStudentException;
import student.session.basic.database.SessionDAO;
import student.session.basic.database.SessionUserDAO;
import student.session.basic.database.TeacherStudentDAO;
import student.session.basic.database.UserDAO;
import student.session.system.form.UserForm;
import student.session.system.session.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Teacher extends User
{
    public Teacher(UserDAO userDAO, SessionDAO sessionDAO, SessionUserDAO sessionUserDAO, TeacherStudentDAO teacherStudentDAO)
    {
        super(userDAO, sessionDAO, sessionUserDAO, teacherStudentDAO);
    }

    public Teacher()
    {
        super();
        setUserIdentity(UserType.TEACHER);
        // TODO Auto-generated constructor stub
    }

    public Teacher(UserForm form)
    {
        super(form);
        setUserIdentity(UserType.TEACHER);
        // TODO Auto-generated constructor stub
    }

    public void insertSession(Session session) throws SessionException
    {
        for (Session s : sessionDAO.getAllSession(this, (Session temp) ->
                temp.getSessionDate().equals(session.getSessionDate())))
        {
            LocalTime startTime1 = s.getSessionStartTime();
            LocalTime endTime1 = s.getSessionEndTime();
            LocalTime startTime2 = session.getSessionStartTime();
            LocalTime endTime2 = session.getSessionEndTime();
            try
            {
                isBetween(startTime1, startTime2, endTime1, endTime2);
            }
            catch (SessionException e)
            {
                throw e;
            }
        }
        sessionDAO.insertSession(this, session);
    }

    public void deleteSession(Long sessionID) throws SessionException
    {
        if (sessionDAO.getSession(sessionID) == null)
            throw new SessionException("No such session");
        sessionUserDAO.deleteSessionUser(sessionDAO.getSession(sessionID));
        sessionDAO.deleteSession(sessionID);
    }


    public void deleteExpiredSession()
    {
        for (Session s : sessionDAO.getAllSession(this, (Session temp) ->
        {
            return temp.getSessionDate().isBefore(LocalDate.now());
        }))
        {
            try
            {
                deleteSession(s.getSessionID());
            }
            catch (SessionException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void addStudent(Student student) throws TeacherStudentException
    {
        for (Student s : teacherStudentDAO.getAllStudent(this))
            if (s.getUserName().equals(student.getUserName()))
                throw new TeacherStudentException("This student is already added!");
        teacherStudentDAO.setStudent(this, student);
    }
    public void addViolatedTimes(Student student)
    {
        teacherStudentDAO.incViolatedTime(this,student);
    }

    public void deleteStudent(Student student)
    {
        teacherStudentDAO.deleteStudent(this, student);
    }

    public ArrayList<Student> getAllStudent()
    {
        return teacherStudentDAO.getAllStudent(this);
    }

    public void replaceSession(Long sessionID, Session newSession)
    {
        newSession.setUser(this);
        sessionDAO.changeSession(this, sessionDAO.getSession(sessionID), newSession);
    }

    private void isBetween(LocalTime startTime1, LocalTime startTime2, LocalTime endTime1, LocalTime endTime2)
            throws SessionException
    {
        if (startTime1.equals(startTime2) || endTime1.equals(endTime2))
            throw new SessionException("Session Time Conflits");
        if (startTime1.isAfter(startTime2) && startTime1.isBefore(endTime2))
            throw new SessionException("Session Time Conflits");
        else if (endTime1.isAfter(startTime2) && endTime1.isBefore(endTime2))
            throw new SessionException("Session Time Conflits");
        else if (startTime2.isAfter(startTime1) && startTime2.isBefore(endTime1))
            throw new SessionException("Session Time Conflits");
        else if (endTime2.isAfter(startTime1) && endTime2.isBefore(endTime1))
            throw new SessionException("Session Time Conflits");
    }
}
