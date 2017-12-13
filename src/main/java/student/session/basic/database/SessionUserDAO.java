package student.session.basic.database;

import java.util.ArrayList;

import student.session.system.session.Session;
import student.session.system.session.SessionUser;
import student.session.system.user.User;

public interface SessionUserDAO
{
	public ArrayList<SessionUser> getAllSessionUser(Session session);
	public ArrayList<SessionUser> getAllSessionUser(User user);
	public SessionUser getSpecificSessionUser(User user, Session session);
	public void insertSessionUser(User user, Session session);
	public void insertSessionUser(SessionUser sessionUser);
	public void changeSessionUserUsedTime(User user, Session session, int newUsedTime);
	public void changeSessionUserTimes(User user, Session session, int newTimes);
}
