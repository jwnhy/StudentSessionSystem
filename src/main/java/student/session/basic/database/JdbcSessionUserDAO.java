package student.session.basic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import student.session.system.session.Session;
import student.session.system.session.SessionUser;
import student.session.system.user.User;
@Service
public class JdbcSessionUserDAO implements SessionUserDAO
{
	@Autowired
	@Qualifier("jdbcSessionDAO")
	SessionDAO sessionDAO;
	@Autowired
	DataSource dataSource;
	@Override
	public ArrayList<SessionUser> getAllSessionUser(Session session)
	{
		// TODO Auto-generated method stub
		Connection connection = null;
		long sessionID = sessionDAO.getSessionID(session.getUser(), session);
		String sql = "SELECT * FROM sessionUserTable WHERE sessionID = ?";
		ArrayList<SessionUser> sessionUserList = new ArrayList<SessionUser>();
		try
		{
			connection = dataSource.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(sql);
			sqlStatement.setLong(1, sessionID);
			ResultSet res = sqlStatement.executeQuery();
			while(res.next())
			{
				SessionUser sessionUser = new SessionUser();
				sessionUser.setSessionID(sessionID);
				sessionUser.setUserName(session.getUser().getUserName());
				sessionUser.setUserTimes(res.getInt("userTimes"));
				sessionUser.setUserUsedTime(res.getInt("userUsedTime"));
				sessionUserList.add(sessionUser);
			}
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
				catch (SQLException e) { }
			}
			
		}
		return sessionUserList;
}

	@Override
	public ArrayList<SessionUser> getAllSessionUser(User user)
	{
		Connection connection = null;
		String sql = "SELECT * FROM sessionUserTable WHERE userName = ?";
		ArrayList<SessionUser> sessionUserList = new ArrayList<SessionUser>();
		try
		{
			connection = dataSource.getConnection();
			PreparedStatement sqlStatement = connection.prepareStatement(sql);
			sqlStatement.setString(1, user.getUserName());
			ResultSet res = sqlStatement.executeQuery();
			while(res.next())
			{
				SessionUser sessionUser = new SessionUser();
				sessionUser.setSessionID(res.getInt("sessionID"));
				sessionUser.setUserName(user.getUserName());
				sessionUser.setUserTimes(res.getInt("userTimes"));
				sessionUser.setUserUsedTime(res.getInt("userUsedTime"));
				sessionUserList.add(sessionUser);
			}
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
				catch (SQLException e) { }
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
		
	}

	@Override
	public void insertSessionUser(SessionUser sessionUser)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeSessionUserUsedTime(User user, Session session, int newUsedTime)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeSessionUserTimes(User user, Session session, int newTimes)
	{
		// TODO Auto-generated method stub
		
	}

}
