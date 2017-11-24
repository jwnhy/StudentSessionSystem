package student.session.basic.database;
import java.util.ArrayList;

import student.session.system.user.*;
public interface UserDAO {
	public User insertUser(User user);
	public void deleteByUserName(String userName);
	public void deleteByPersonName(String personName);
	public User findByUserName(String userName);
	public User findByPersonName(String personName);
	public void changeByUserName(String userName, String columnName);
	public ArrayList<User> getAllUser();
}

