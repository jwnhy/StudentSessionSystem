package student.session.basic.database;
import java.util.ArrayList;

import student.session.system.user.*;
public interface UserDAO {
	public User insertUser(User user);
	public void deleteByUserName(String userName);
	public void deleteByPersonName(String personName);
	public User findByUserName(String userName);
	public User findByPersonName(String personName);
	public void changeIdentityByUserName(String userName, String columnName);
	public void changeIntroductionByUserName(String userName, String introduction);
	public ArrayList<User> getAllUser();
}

