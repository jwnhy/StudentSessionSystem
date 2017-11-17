package student.session.basic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import student.session.system.user.User;

public class JdbcUserDAO implements UserDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		Connection connection = null;
		String sql = "INSERT INTO userTable " +
				"(userName, userPassword, personName) VALUES (?, ?, ?)";
		try 
		{
			connection = dataSource.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(sql);
			sqlStatement.setString(1, user.getUserName());
			sqlStatement.setString(2, user.getUserPassword());
			sqlStatement.setString(3, user.getPersonName());
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
	}

	@Override
	public void deleteByUserName(String userName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByPersonName(String personName) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByPersonName(String personName) {
		// TODO Auto-generated method stub
		return null;
	}

}