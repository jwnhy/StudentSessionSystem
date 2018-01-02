package student.session.basic.database;

import student.session.system.session.Session;
import student.session.system.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;

public interface SessionDAO
{
    void insertSession(User user, Session session);

    void changeSession(User user, Session oldSession, Session newSession);

    ArrayList<Session> getAllSession(User user);

    ArrayList<Session> getAllSession(User user, Function<Session, Boolean> condition);


    Session getSession(Long sessionID);

    void deleteSession(Long sessionID);

    ArrayList<Session> getAllSession(User user, LocalDate sessionDate);
}
