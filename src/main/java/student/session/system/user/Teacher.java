package student.session.system.user;

import java.time.LocalTime;

import student.session.basic.SessionException;
import student.session.basic.database.SessionDAO;
import student.session.system.form.UserForm;
import student.session.system.session.Session;

public class Teacher extends User
{
	private SessionDAO sessionDAO;

	public void setSessionDAO(SessionDAO sessionDAO)
	{
		this.sessionDAO = sessionDAO;
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
		if (sessionDAO.getSessionID(this, session) != 0)
			throw new SessionException("Insert Session Conflits Same Session!");
		for (Session s : sessionDAO.getAllSession(this, (Session temp) ->
		{
			return temp.getSessionDate().equals(session.getSessionDate());
		}))
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
