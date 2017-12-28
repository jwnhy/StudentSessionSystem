package student.session.basic.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import student.session.system.controller.HomeController;
import student.session.system.session.Session;
import student.session.system.user.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Function;

@Repository
public class JdbcSessionDAO implements SessionDAO
{
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    UserDAO userDAO;

    @Override
    public void insertSession(User user, Session session)
    {
        Connection connection = null;
        String sql = "INSERT INTO sessionTable"
                + "(userName, sessionStartTime, sessionEndTime, sessionAddress, sessionDate, timesLimit, totalTimeLimit, sessionID)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, UUID_SHORT())";
        // TODO Auto-generated method stub
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, user.getUserName());
            sqlStatement.setString(2, session.getSessionStartTime().format(timeFormatter));
            sqlStatement.setString(3, session.getSessionEndTime().format(timeFormatter));
            sqlStatement.setString(4, session.getSessionAddress());
            sqlStatement.setString(5, session.getSessionDate().format(dateFormatter));
            sqlStatement.setInt(6, session.getTimesLimit());
            sqlStatement.setInt(7, session.getTotalTimeLimit());
            sqlStatement.executeUpdate();
            sqlStatement.close();
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
    public void deleteSession(User user, Session session)
    {
        // TODO Auto-generated method stub
        ArrayList<Session> sessionList = getAllSession(user);
        Connection connection = null;
        String sql = "DELETE FROM sessionTable WHERE userName = ? AND sessionStartTime = ? AND sessionDate = ?";
        if (sessionList.indexOf(session) == -1)
        {
            logger.error("Delete Session Failed No Such Session");
            return;
        }
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, user.getUserName());
            sqlStatement.setString(2, session.getSessionStartTime().format(timeFormatter));
            sqlStatement.setString(3, session.getSessionDate().format(dateFormatter));
            sqlStatement.executeUpdate();
            sqlStatement.close();
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
    public void changeSession(User user, Session oldSession, Session newSession)
    {
        // TODO Auto-generated method stub
        String sql = "UPDATE sessionTable SET sessionStartTime = ?, sessionEndTime = ?, "
                + "sessionAddress = ?, sessionDate = ?, " + "timesLimit = ?, totalTimeLimit = ? "
                + "WHERE userName = ? AND sessionStartTime = ? AND sessionDate = ?";
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, newSession.getSessionStartTime().format(timeFormatter));
            sqlStatement.setString(2, newSession.getSessionEndTime().format(timeFormatter));
            sqlStatement.setString(3, newSession.getSessionAddress());
            sqlStatement.setString(4, newSession.getSessionDate().format(dateFormatter));
            sqlStatement.setInt(5, newSession.getTimesLimit());
            sqlStatement.setInt(6, newSession.getTotalTimeLimit());
            sqlStatement.setString(7, user.getUserName());
            sqlStatement.setString(8, oldSession.getSessionStartTime().format(timeFormatter));
            sqlStatement.setString(9, oldSession.getSessionDate().format(dateFormatter));
            sqlStatement.executeUpdate();
            sqlStatement.close();
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
    public ArrayList<Session> getAllSession(User user)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        String sql = "SELECT * FROM sessionTable WHERE userName = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, user.getUserName());
            ResultSet res = sqlStatement.executeQuery();
            ArrayList<Session> sessionList = new ArrayList<Session>();
            while (res.next())
            {
                Session session = new Session();
                session.setUser(user);
                session.setSessionStartTime(LocalTime.parse(res.getString("sessionStartTime")));
                session.setSessionEndTime(LocalTime.parse(res.getString("sessionEndTime")));
                session.setSessionDate(LocalDate.parse(res.getString("sessionDate")));
                session.setSessionAddress(res.getString("sessionAddress"));
                session.setTimesLimit(res.getByte("timesLimit"));
                session.setTotalTimeLimit(res.getByte("totalTimeLimit"));
                session.setSessionID(res.getLong("sessionID"));
                sessionList.add(session);
            }
            res.close();
            sqlStatement.close();
            return sessionList;
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
    public long getSessionID(User user, Session session)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        String sql = "SELECT sessionID FROM sessionTable WHERE userName = ? AND sessionStartTime = ? AND sessionDate = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, user.getUserName());
            sqlStatement.setString(2, session.getSessionStartTime().toString());
            sqlStatement.setString(3, session.getSessionDate().toString());
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
        return 0;
    }

    @Override
    public Session getSession(Long sessionID)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        String sql = "SELECT * FROM sessionTable WHERE sessionID = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setLong(1, sessionID);
            ResultSet res = sqlStatement.executeQuery();
            Session session = null;
            while (res.next())
            {
                session = new Session();
                session.setUser(userDAO.findByUserName(res.getString("userName")));
                session.setSessionStartTime(LocalTime.parse(res.getString("sessionStartTime")));
                session.setSessionEndTime(LocalTime.parse(res.getString("sessionEndTime")));
                session.setSessionDate(LocalDate.parse(res.getString("sessionDate")));
                session.setSessionAddress(res.getString("sessionAddress"));
                session.setTimesLimit(res.getByte("timesLimit"));
                session.setTotalTimeLimit(res.getByte("totalTimeLimit"));
                session.setSessionID(res.getLong("sessionID"));

            }
            res.close();
            sqlStatement.close();
            return session;
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
    public ArrayList<Session> getAllSession(User user, LocalDate sessionDate)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        String sql = "SELECT * FROM sessionTable WHERE userName = ? AND sessionDate = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, user.getUserName());
            sqlStatement.setString(2, sessionDate.format(dateFormatter));
            ResultSet res = sqlStatement.executeQuery();
            ArrayList<Session> sessionList = new ArrayList<Session>();
            while (res.next())
            {
                Session session = new Session();
                session.setUser(user);
                session.setSessionStartTime(LocalTime.parse(res.getString("sessionStartTime")));
                session.setSessionEndTime(LocalTime.parse(res.getString("sessionEndTime")));
                session.setSessionDate(LocalDate.parse(res.getString("sessionDate")));
                session.setSessionAddress(res.getString("sessionAddress"));
                session.setTimesLimit(res.getByte("timesLimit"));
                session.setTotalTimeLimit(res.getByte("totalTimeLimit"));
                session.setSessionID(res.getLong("sessionID"));

                sessionList.add(session);
            }
            res.close();
            sqlStatement.close();
            return sessionList;
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
    public ArrayList<Session> getAllSession(User user, Function<Session, Boolean> condition)
    {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        Connection connection = null;
        String sql = "SELECT * FROM sessionTable WHERE userName = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, user.getUserName());
            ResultSet res = sqlStatement.executeQuery();
            ArrayList<Session> sessionList = new ArrayList<Session>();
            while (res.next())
            {
                Session session = new Session();
                session.setUser(user);
                session.setSessionStartTime(LocalTime.parse(res.getString("sessionStartTime")));
                session.setSessionEndTime(LocalTime.parse(res.getString("sessionEndTime")));
                session.setSessionDate(LocalDate.parse(res.getString("sessionDate")));
                session.setSessionAddress(res.getString("sessionAddress"));
                session.setTimesLimit(res.getByte("timesLimit"));
                session.setTotalTimeLimit(res.getByte("totalTimeLimit"));
                session.setSessionID(res.getLong("sessionID"));
                if (condition.apply(session))
                    sessionList.add(session);
            }
            res.close();
            sqlStatement.close();
            return sessionList;
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
    public void deleteSession(Long sessionID)
    {
        Connection connection = null;
        String sql = "DELETE FROM sessionTable WHERE sessionID = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setLong(1, sessionID);
            sqlStatement.executeUpdate();
            sqlStatement.close();
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
