package student.session.basic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import student.session.system.user.Manager;
import student.session.system.user.Student;
import student.session.system.user.Teacher;
import student.session.system.user.User;
import student.session.system.user.UserType;
@Service
public class JdbcUserDAO implements UserDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;



	@Override
	public User insertUser(User user) {
		// TODO Auto-generated method stub
		Connection connection = null;
		String sql = "INSERT INTO userTable " +
				"(userName, userPassword, personName, userIdentity) VALUES (?, ?, ?, ?)";
		if(findByUserName(user.getUserName())!=null)
		{
			return null;
		}
		try 
		{
			connection = dataSource.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(sql);
			sqlStatement.setString(1, user.getUserName());
			sqlStatement.setString(2, user.getUserPassword());
			sqlStatement.setString(3, user.getPersonName());
			if(user.getUserIdentity()!=null)
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
			if(connection!=null)
			{
				try 
				{
					connection.close();
				} 
				catch (SQLException e) { }
			}
		}
		return user;
	}

	@Override
	public void deleteByUserName(String userName) 
	{
		// TODO Auto-generated method stub
		String sql = "DELETE FROM userTable WHERE userName = ?";
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(sql);
			sqlStatement.setString(1, userName);
			sqlStatement.executeUpdate();
			sqlStatement.close();
		}
		catch (SQLException exception)
		{
			throw new RuntimeException(exception);
		}
		finally
		{
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException exception) { }
			}
		}
	}

	@Override
	public void deleteByPersonName(String personName)
	{
		// TODO Auto-generated method stub
		String sql = "DELETE FROM userTable WHERE personName = ?";
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(sql);
			sqlStatement.setString(1, personName);
			sqlStatement.executeUpdate();
			sqlStatement.close();
		}
		catch (SQLException exception)
		{
			throw new RuntimeException(exception);
		}
		finally
		{
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException exception) { }
			}
		}
	}

	@Override
	public User findByUserName(String userName)
	{
		String sql = "SELECT * FROM userTable WHERE userName = ?";
		Connection connection = null;
		// TODO Auto-generated method stub
		try
		{
			connection = dataSource.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(sql);
			sqlStatement.setString(1, userName);
			ResultSet res = sqlStatement.executeQuery();
			User userResult = null;
			if(res.next())
			{
				switch(UserType.valueOf(res.getString("userIdentity")))
				{
				case MANAGER:
					userResult = new Manager();
					break;
				case STUDENT:
					userResult = new Student();
					break;
				case TEACHER:
					userResult = new Teacher();
					break;
				case UNKNOWN:
					userResult = new User();
					break;
				default:
					break;
				}
				userResult.setPersonName(res.getString("personName"));
				userResult.setUserIdentity(UserType.valueOf(res.getString("userIdentity")));
				userResult.setUserName(res.getString("userName"));
				userResult.setUserPassword(res.getString("userPassword"));
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
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException exception) { }
			}
		}
	}

	@Override
	public User findByPersonName(String personName)
	{
		// TODO Auto-generated method stub
		return null;
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
			ArrayList<User> users = new ArrayList<User>();
			while(res.next())
			{
				user = new User();
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
		catch(SQLException exception)
		{
			throw new RuntimeException(exception);
		}
		finally
		{
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch(SQLException exception) { }
			}
		}
	}

	@Override
	public void changeIdentityByUserName(String userName, String columnName)
	{
		// TODO Auto-generated method stub
		String sql = "UPDATE userTable SET userIdentity = ? WHERE userName = ?";
		if(findByUserName(userName) == null)
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
		catch(SQLException sqlException)
		{
			throw new RuntimeException(sqlException);
		}
		finally
		{
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch(SQLException exception) { }
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
		catch(SQLException sqlException)
		{
			throw new RuntimeException(sqlException);
		}
		finally
		{
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch(SQLException exception) { }
			}
		}
		
	}

	@Override
	public void changeAvailableDateLimitByUserName(String userName, LocalDate availableDateLimit)
	{
		// TODO Auto-generated method stub
		Connection connection = null;
		String sql = "UPDATE userTable SET availableDateLimit = ? WHERE userName = ?";
		try
		{
			connection = dataSource.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(sql);
			sqlStatement.setString(1, availableDateLimit.toString());
			sqlStatement.setString(2, userName);
			sqlStatement.executeUpdate();
			sqlStatement.close();
		}
		catch(SQLException sqlException)
		{
			throw new RuntimeException(sqlException);
		}
		finally
		{
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch(SQLException exception) { }
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
		catch(SQLException sqlException)
		{
			throw new RuntimeException(sqlException);
		}
		finally
		{
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch(SQLException exception) { }
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
			ArrayList<User> users = new ArrayList<User>();
			while(res.next())
			{
				user = new User();
				user.setPersonName(res.getString("personName"));
				user.setUserIdentity(UserType.valueOf(res.getString("userIdentity")));
				user.setUserName(res.getString("userName"));
				user.setUserPassword(res.getString("userPassword"));
				if(condition.apply(user))
					users.add(user);
			}
			res.close();
			sqlStatement.close();
			return users;
		}
		catch(SQLException exception)
		{
			throw new RuntimeException(exception);
		}
		finally
		{
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch(SQLException exception) { }
			}
		}
	}

}
