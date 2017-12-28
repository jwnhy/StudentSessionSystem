package student.session.basic.database;

import student.session.system.session.Session;
import student.session.system.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;

public interface SessionDAO
{
    public void insertSession(User user, Session session);

    public void deleteSession(User user, Session session);

    public void changeSession(User user, Session oldSession, Session newSession);

    public ArrayList<Session> getAllSession(User user);

    public ArrayList<Session> getAllSession(User user, Function<Session, Boolean> condition);

    public long getSessionID(User user, Session session);

    Session getSession(Long sessionID);

    public void deleteSession(Long sessionID);

    ArrayList<Session> getAllSession(User user, LocalDate sessionDate);
}
