package student.session.basic.database;
import student.session.system.user.*;
public interface UserDAO {
	public void insertUser(User user);
	public void deleteByUserName(String userName);
	public void deleteByPersonName(String personName);
	public User findByUserName(String userName);
	public User findByPersonName(String personName);
}

