package student.session.basic.database;

import student.session.system.session.Session;
import student.session.system.session.SessionUser;
import student.session.system.user.User;

import java.util.ArrayList;
import java.util.function.Function;

public interface SessionUserDAO
{
    ArrayList<SessionUser> getAllSessionUser(Session session);

    ArrayList<SessionUser> getAllSessionUser(User user);

    ArrayList<SessionUser> getAllSessionUser(User user, Function<SessionUser, Boolean> condition);

    SessionUser getSpecificSessionUser(User user, Session session);

    void insertSessionUser(User user, Session session);

    void insertSessionUser(SessionUser sessionUser);

    void deleteSessionUser(User user, Session session);

    void deleteSessionUser(SessionUser sessionUser);
}
