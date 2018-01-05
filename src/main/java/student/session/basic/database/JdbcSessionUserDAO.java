package student.session.basic.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import student.session.system.session.Session;
import student.session.system.session.SessionUser;
import student.session.system.user.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

@Repository
public class JdbcSessionUserDAO implements SessionUserDAO
{
    @Autowired()
    @Qualifier("jdbcSessionDAO")
    SessionDAO sessionDAO;
    @Autowired
    DataSource dataSource;

    @Override
    public ArrayList<SessionUser> getAllSessionUser(Session session)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        String sql = "SELECT * FROM sessionUserTable WHERE sessionID = ?";
        ArrayList<SessionUser> sessionUserList = new ArrayList<>();
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setLong(1, session.getSessionID());
            ResultSet res = sqlStatement.executeQuery();
            while (res.next())
            {
                SessionUser sessionUser = new SessionUser();
                sessionUser.setSessionID(session.getSessionID());
                sessionUser.setUserName(res.getString("userName"));
                sessionUserList.add(sessionUser);
            }
        }
        catch (SQLException exception)
        {
            throw new RuntimeException(exception);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
            }

        }
        return sessionUserList;
    }

    @Override
    public ArrayList<SessionUser> getAllSessionUser(User user)
    {
        return getAllSessionUser(user,(SessionUser temp)->true);
    }
    @Override
    public ArrayList<SessionUser> getAllSessionUser(User user, Function<SessionUser, Boolean> condition)
    {
        Connection connection = null;
        String sql = "SELECT * FROM sessionUserTable WHERE userName = ?";
        ArrayList<SessionUser> sessionUserList = new ArrayList<>();
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, user.getUserName());
            ResultSet res = sqlStatement.executeQuery();
            while (res.next())
            {
                SessionUser sessionUser = new SessionUser();
                sessionUser.setSessionID(res.getLong("sessionID"));
                sessionUser.setUserName(res.getString("userName"));
                if(condition.apply(sessionUser))
                    sessionUserList.add(sessionUser);
            }
        }
        catch (SQLException exception)
        {
            throw new RuntimeException(exception);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
            }

        }
        return sessionUserList;
    }

    @Override
    public SessionUser getSpecificSessionUser(User user, Session session)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insertSessionUser(User user, Session session)
    {
        // TODO Auto-generated method stub
        insertSessionUser(new SessionUser(user, session));
    }

    @Override
    public void insertSessionUser(SessionUser sessionUser)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        String sql = "INSERT INTO sessionUserTable " +
                " (`userName`, `sessionID`) " +
                "VALUES (?, ?)";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, sessionUser.getUserName());
            sqlStatement.setLong(2, sessionUser.getSessionID());
            sqlStatement.execute();
        }
        catch (SQLException exception)
        {
            throw new RuntimeException(exception);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
            }

        }
    }

    @Override
    public void deleteSessionUser(User user, Session session)
    {
        deleteSessionUser(new SessionUser(user, session));
    }

    @Override
    public void deleteSessionUser(SessionUser sessionUser)
    {
        Connection connection = null;
        String sql = "DELETE FROM sessionUserTable WHERE userName = ? AND sessionID = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, sessionUser.getUserName());
            sqlStatement.setLong(2, sessionUser.getSessionID());
            sqlStatement.execute();
        }
        catch (SQLException exception)
        {
            throw new RuntimeException(exception);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
            }

        }
    }

    @Override
    public void deleteSessionUser(Session session)
    {
        Connection connection = null;
        String sql = "DELETE FROM sessionUserTable WHERE sessionID = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setLong(1, session.getSessionID());
            sqlStatement.execute();
        }
        catch (SQLException exception)
        {
            throw new RuntimeException(exception);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
            }

        }
    }
    @Override
    public void deleteSessionUser(User user)
    {
        Connection connection = null;
        String sql = "DELETE FROM sessionUserTable WHERE userName = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, user.getUserName());
            sqlStatement.execute();
        }
        catch (SQLException exception)
        {
            throw new RuntimeException(exception);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
            }

        }
    }


}
