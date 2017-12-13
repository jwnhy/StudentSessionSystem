package student.session.basic.database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;

import student.session.system.session.Session;
import student.session.system.user.User;

public interface SessionDAO
{
	public void insertSession(User user, Session session);
	public void deleteSession(User user, Session session);
	public void changeSession(User user, Session oldSession, Session newSession);
	public ArrayList<Session> getAllSession(User user);
	public ArrayList<Session> getAllSession(User user, Function<Session, Boolean> condition);
	public long getSessionID(User user, Session session);
	Session getSession(Long sessionID);
	ArrayList<Session> getAllSession(User user, LocalDate sessionDate);
}
