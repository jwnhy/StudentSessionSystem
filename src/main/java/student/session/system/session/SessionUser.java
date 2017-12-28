package student.session.system.session;

import student.session.system.user.User;

public class SessionUser
{
    String userName;
    Long sessionID;
    int userTimes;
    int userUsedTime;
    int violatedTimes;

    public SessionUser(User user, Session session)
    {
        userName = user.getUserName();
        sessionID = session.getSessionID();
        userTimes = 0;
        userUsedTime = 0;
        violatedTimes = 0;
    }

    public SessionUser()
    {

    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Long getSessionID()
    {
        return sessionID;
    }

    public void setSessionID(long sessionID)
    {
        this.sessionID = sessionID;
    }

    public void setSessionID(Long sessionID)
    {
        this.sessionID = sessionID;
    }

    public int getUserTimes()
    {
        return userTimes;
    }

    public void setUserTimes(int userTimes)
    {
        this.userTimes = userTimes;
    }

    public int getUserUsedTime()
    {
        return userUsedTime;
    }

    public void setUserUsedTime(int userUsedTime)
    {
        this.userUsedTime = userUsedTime;
    }

    public int getViolatedTimes()
    {
        return violatedTimes;
    }

    public void setViolatedTimes(int violatedTimes)
    {
        this.violatedTimes = violatedTimes;
    }
}
