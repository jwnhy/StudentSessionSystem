package student.session.basic.database;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;

import student.session.system.user.*;
public interface UserDAO {
	public User insertUser(User user);
	
	public void deleteByUserName(String userName);
	public void deleteByPersonName(String personName);
	
	public User findByUserName(String userName);
	public User findByPersonName(String personName);
	
	public void changeIdentityByUserName(String userName, String columnName);
	public void changeIntroductionByUserName(String userName, String introduction);
	public void changeAvailableDateLimitByUserName(String userName, LocalDate availableDateLimit);
	public void changeAddressByUserName(String userName, String address);
	
	public ArrayList<User> getAllUser();
	public ArrayList<User> getAllUser(Function<User, Boolean> condition);

}

