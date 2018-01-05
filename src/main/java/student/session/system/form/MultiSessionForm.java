package student.session.system.form;

public class MultiSessionForm
{
    private String sessionStartDate;
    private String sessionEndDate;
    private String sessionStartTime;
    private String sessionEndTime;
    private String sessionAddress;
    private String dayOfWeek;
    private int timesLimit;        //max times one can visit
    private int totalTimeLimit;    //total max time one can visit

    public String getSessionStartDate()
    {
        return sessionStartDate;
    }

    public void setSessionStartDate(String sessionStartDate)
    {
        this.sessionStartDate = sessionStartDate;
    }

    public String getSessionEndDate()
    {
        return sessionEndDate;
    }

    public void setSessionEndDate(String sessionEndDate)
    {
        this.sessionEndDate = sessionEndDate;
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

    public String getDayOfWeek()
    {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
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

