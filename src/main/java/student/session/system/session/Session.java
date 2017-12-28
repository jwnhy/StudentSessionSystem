package student.session.system.session;

import student.session.system.form.SessionForm;
import student.session.system.user.User;

import java.time.LocalDate;
import java.time.LocalTime;

public class Session
{
    LocalDate sessionDate;
    LocalTime sessionStartTime;
    LocalTime sessionEndTime;
    String sessionAddress;
    Long sessionID;
    int timesLimit;        //max times one can visit
    int totalTimeLimit;    //total max time one can visit
    User user;

    public Session()
    {

    }

    public Session(SessionForm sessionForm)
    {
        sessionDate = LocalDate.parse(sessionForm.getSessionDate());
        sessionStartTime = LocalTime.parse(sessionForm.getSessionStartTime());
        sessionEndTime = LocalTime.parse(sessionForm.getSessionEndTime());
        sessionAddress = sessionForm.getSessionAddress();
        timesLimit = sessionForm.getTimesLimit();
        totalTimeLimit = sessionForm.getTotalTimeLimit();
    }

    public Long getSessionID()
    {
        return sessionID;
    }

    public void setSessionID(Long sessionID)
    {
        this.sessionID = sessionID;
    }

    public LocalTime getTimeLength()
    {
        return (sessionEndTime.minusHours(sessionStartTime.getHour())
                .minusMinutes(sessionStartTime.getMinute()));
    }

    public int getTimesLimit()
    {
        return timesLimit;
    }

    public void setTimesLimit(int timesLimit)
    {
        this.timesLimit = timesLimit;
    }

    public int getTotalTimeLimit()
    {
        return totalTimeLimit;
    }

    public void setTotalTimeLimit(int totalTimeLimit)
    {
        this.totalTimeLimit = totalTimeLimit;
    }

    public LocalDate getSessionDate()
    {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate)
    {
        this.sessionDate = sessionDate;
    }

    public LocalTime getSessionStartTime()
    {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalTime sessionStartTime)
    {
        this.sessionStartTime = sessionStartTime;
    }

    public LocalTime getSessionEndTime()
    {
        return sessionEndTime;
    }

    public void setSessionEndTime(LocalTime sessionEndTime)
    {
        this.sessionEndTime = sessionEndTime;
    }

    public String getSessionAddress()
    {
        return sessionAddress;
    }

    public void setSessionAddress(String sessionAddress)
    {
        this.sessionAddress = sessionAddress;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
