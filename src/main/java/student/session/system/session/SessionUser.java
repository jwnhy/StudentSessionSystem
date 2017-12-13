package student.session.system.session;

public class SessionUser
{
	String userName;
	Long sessionID;
	int userTimes;
	int userUsedTime;
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
}
