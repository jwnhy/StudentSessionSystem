package student.session.basic.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import student.session.system.user.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

@Repository
public class JdbcUserDAO implements UserDAO
{
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private SessionUserDAO sessionUserDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TeacherStudentDAO teacherStudentDAO;

    private User getUser(String name, Connection connection, String sql)
    {
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, name);
            ResultSet res = sqlStatement.executeQuery();
            User userResult = null;
            if (res.next())
            {
                userResult = newUser(UserType.valueOf(res.getString("userIdentity")));
                userResult.setPersonName(res.getString("personName"));
                userResult.setUserIdentity(UserType.valueOf(res.getString("userIdentity")));
                userResult.setUserName(res.getString("userName"));
                userResult.setUserPassword(res.getString("userPassword"));
                userResult.setUserAddress(res.getString("userAddress"));
                userResult.setUserIntroduction(res.getString("userIntroduction"));
            }
            res.close();
            sqlStatement.close();
            return userResult;
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
                catch (SQLException exception)
                {
                }
            }
        }
    }

    private void deleteUser(String name, String sql, Connection connection)
    {
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, name);
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
                catch (SQLException exception)
                {
                }
            }
        }
    }

    @Override
    public void insertUser(User user)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        String sql = "INSERT INTO userTable " +
                "(userName, userPassword, personName, userIdentity) VALUES (?, ?, ?, ?)";
        if (findByUserName(user.getUserName()) != null)
        {
            return;
        }
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, user.getUserName());
            sqlStatement.setString(2, user.getUserPassword());
            sqlStatement.setString(3, user.getPersonName());
            if (user.getUserIdentity() != null)
                sqlStatement.setString(4, user.getUserIdentity().name());
            else sqlStatement.setString(4, UserType.UNKNOWN.name());
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
    public void deleteByUserName(String userName)
    {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM userTable WHERE userName = ?";
        Connection connection = null;
        deleteUser(userName, sql, connection);
    }

    @Override
    public void deleteByPersonName(String personName)
    {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM userTable WHERE personName = ?";
        Connection connection = null;
        deleteUser(personName, sql, connection);
    }


    private User newUser(UserType id)
    {
        User userResult = null;
        switch (id)
        {
            case ADMIN:
                userResult = new Admin(userDAO, sessionDAO, sessionUserDAO, teacherStudentDAO);
                break;
            case STUDENT:
                userResult = new Student(userDAO, sessionDAO, sessionUserDAO, teacherStudentDAO);
                break;
            case TEACHER:
                userResult = new Teacher(userDAO, sessionDAO, sessionUserDAO, teacherStudentDAO);
                break;
            case UNKNOWN:
                userResult = new User(userDAO, sessionDAO, sessionUserDAO, teacherStudentDAO);
                break;
            default:
                break;
        }
        return userResult;
    }

    @Override
    public User findByUserName(String userName)
    {
        String sql = "SELECT * FROM userTable WHERE userName = ?";
        Connection connection = null;
        // TODO Auto-generated method stub
        return getUser(userName, connection, sql);
    }

    @Override
    public User findByPersonName(String personName)
    {
        String sql = "SELECT * FROM userTable WHERE personName = ?";
        Connection connection = null;
        // TODO Auto-generated method stub
        return getUser(personName, connection, sql);
    }


    @Override
    public ArrayList<User> getAllUser()
    {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM userTable";
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            ResultSet res = sqlStatement.executeQuery();
            User user = null;
            ArrayList<User> users = new ArrayList<>();
            while (res.next())
            {
                user = newUser(UserType.valueOf(res.getString("userIdentity")));
                user.setPersonName(res.getString("personName"));
                user.setUserIdentity(UserType.valueOf(res.getString("userIdentity")));
                user.setUserName(res.getString("userName"));
                user.setUserPassword(res.getString("userPassword"));
                users.add(user);
            }
            res.close();
            sqlStatement.close();
            return users;
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
                catch (SQLException exception)
                {
                }
            }
        }
    }

    @Override
    public void changeIdentityByUserName(String userName, String columnName)
    {
        // TODO Auto-generated method stub
        String sql = "UPDATE userTable SET userIdentity = ? WHERE userName = ?";
        if (findByUserName(userName) == null)
            return;
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, columnName);
            sqlStatement.setString(2, userName);
            sqlStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            throw new RuntimeException(sqlException);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException exception)
                {
                }
            }
        }

    }

    @Override
    public void changeIntroductionByUserName(String userName, String introduction)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        String sql = "UPDATE userTable SET userIntroduction = ? WHERE userName = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, introduction);
            sqlStatement.setString(2, userName);
            sqlStatement.executeUpdate();
            sqlStatement.close();
        }
        catch (SQLException sqlException)
        {
            throw new RuntimeException(sqlException);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException exception)
                {
                }
            }
        }

    }


    @Override
    public void changeAddressByUserName(String userName, String address)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        String sql = "UPDATE userTable SET userAddress = ? WHERE userName = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, address);
            sqlStatement.setString(2, userName);
            sqlStatement.executeUpdate();
            sqlStatement.close();
        }
        catch (SQLException sqlException)
        {
            throw new RuntimeException(sqlException);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException exception)
                {
                }
            }
        }
    }

    @Override
    public ArrayList<User> getAllUser(Function<User, Boolean> condition)
    {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM userTable";
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            ResultSet res = sqlStatement.executeQuery();
            User user = null;
            ArrayList<User> users = new ArrayList<>();
            while (res.next())
            {
                user = newUser(UserType.valueOf(res.getString("userIdentity")));
                user.setPersonName(res.getString("personName"));
                user.setUserIdentity(UserType.valueOf(res.getString("userIdentity")));
                user.setUserName(res.getString("userName"));
                user.setUserPassword(res.getString("userPassword"));
                if (condition.apply(user))
                    users.add(user);
            }
            res.close();
            sqlStatement.close();
            return users;
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
                catch (SQLException exception)
                {
                }
            }
        }
    }

    @Override
    public void changeUserInfo(User newUser)
    {
        Connection connection = null;
        String sql = "UPDATE userTable SET userPassword = ?, personName = ?, userAddress = ?, userIntroduction = ? WHERE userName = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1,newUser.getUserPassword());
            sqlStatement.setString(2,newUser.getPersonName());
            sqlStatement.setString(3,newUser.getUserAddress());
            sqlStatement.setString(4,newUser.getUserIntroduction());
            sqlStatement.setString(5,newUser.getUserName());
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
                catch (SQLException exception)
                {
                }
            }
        }
    }

}
