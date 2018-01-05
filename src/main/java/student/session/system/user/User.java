package student.session.system.user;

import org.springframework.stereotype.Service;
import student.session.basic.InfoException;
import student.session.basic.database.SessionDAO;
import student.session.basic.database.SessionUserDAO;
import student.session.basic.database.TeacherStudentDAO;
import student.session.basic.database.UserDAO;
import student.session.system.form.UserForm;

import java.util.regex.Pattern;

@Service
public class User
{
    UserDAO userDAO;
    SessionDAO sessionDAO;
    SessionUserDAO sessionUserDAO;
    TeacherStudentDAO teacherStudentDAO;
    private String userName;
    private String userPassword;
    private String personName;
    private UserType userIdentity;
    private String userAddress;
    private String userIntroduction;

    public User(UserDAO userDAO, SessionDAO sessionDAO, SessionUserDAO sessionUserDAO, TeacherStudentDAO teacherStudentDAO)
    {
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
        this.sessionUserDAO = sessionUserDAO;
        this.teacherStudentDAO = teacherStudentDAO;
    }

    public User()
    {

    }

    public User(UserForm form)
    {
        this.setUserName(form.getUserName());
        this.setUserPassword(form.getUserPassword());
        this.setPersonName(form.getPersonName());
    }

    public String getUserAddress()
    {
        return userAddress;
    }

    public void setUserAddress(String userAddress)
    {
        this.userAddress = userAddress;
    }

    public String getUserIntroduction()
    {
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction)
    {
        this.userIntroduction = userIntroduction;
    }

    public void alterUser(UserForm form) throws InfoException
    {
        Pattern userPasswordPattern = Pattern.compile("^\\w{5,16}");
        if (userPasswordPattern.matcher(form.getUserPassword()).matches())
            this.setUserPassword(form.getUserPassword());
        else throw new InfoException("Info is not right");
        if(form.getPersonName().length()>90||form.getUserAddress().length()>90||form.getUserIntroduction().length()>90)
            throw new InfoException("Info is too long");
        this.setPersonName(form.getPersonName()==null?" ":form.getPersonName());
        this.setUserAddress(form.getUserAddress()==null?" ":form.getUserAddress());
        this.setUserIntroduction(form.getUserIntroduction()==null?" ":form.getUserIntroduction());
        userDAO.changeUserInfo(this);
    }

    public String getPersonName()
    {
        return personName;
    }

    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public UserType getUserIdentity()
    {
        return userIdentity;
    }

    public void setUserIdentity(UserType userIdentity)
    {
        this.userIdentity = userIdentity;
    }

    public SessionDAO getSessionDAO()
    {
        return sessionDAO;
    }

    public void setSessionDAO(SessionDAO sessionDAO)
    {
        this.sessionDAO = sessionDAO;
    }

    public SessionUserDAO getSessionUserDAO()
    {
        return sessionUserDAO;
    }

    public void setSessionUserDAO(SessionUserDAO sessionUserDAO)
    {
        this.sessionUserDAO = sessionUserDAO;
    }

    public TeacherStudentDAO getTeacherStudentDAO()
    {
        return teacherStudentDAO;
    }

    public void setTeacherStudentDAO(TeacherStudentDAO teacherStudentDAO)
    {
        this.teacherStudentDAO = teacherStudentDAO;
    }

    public boolean isValid()
    {
        Pattern userNamePattern = Pattern.compile("^\\w{5,10}");
        Pattern userPasswordPattern = Pattern.compile("^\\w{5,16}");
        return userNamePattern.matcher(userName).matches() &&
                userPasswordPattern.matcher(userPassword).matches();
    }
}
