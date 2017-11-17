package student.session.basic.database;

import javax.activation.DataSource;

import student.session.system.user.User;

public class JdbcUserDAO implements UserDAO {

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
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
