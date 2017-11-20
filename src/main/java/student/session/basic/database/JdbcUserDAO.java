package student.session.basic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import student.session.system.user.User;

public class JdbcUserDAO implements UserDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

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
			sqlStatement.setString(4, user.getUserIdentity().name());
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
				userResult = new User();
				userResult.setPersonName(res.getString("personName"));
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

}
