package student.session.system.form;


public class SessionForm
{
    private String sessionDate;
    private String sessionStartTime;
    private String sessionEndTime;
    private String sessionAddress;
    private int timesLimit;        //max times one can visit
    private int totalTimeLimit;    //total max time one can visit

    public String getSessionDate()
    {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate)
    {
        this.sessionDate = sessionDate;
    }

    public String getSessionStartTime()
    {
        return sessionStartTime;
    }

    public void setSessionStartTime(String sessionStartTime)
    {
        this.sessionStartTime = sessionStartTime;
    }

    public String getSessionEndTime()
    {
        return sessionEndTime;
    }

    public void setSessionEndTime(String sessionEndTime)
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
}
