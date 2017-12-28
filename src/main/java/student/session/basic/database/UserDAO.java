package student.session.basic.database;

import student.session.system.user.User;

import java.util.ArrayList;
import java.util.function.Function;

public interface UserDAO
{
    User insertUser(User user);

    void deleteByUserName(String userName);

    void deleteByPersonName(String personName);

    User findByUserName(String userName);

    User findByPersonName(String personName);

    void changeIdentityByUserName(String userName, String columnName);

    void changeIntroductionByUserName(String userName, String introduction);

    void changeAddressByUserName(String userName, String address);

    ArrayList<User> getAllUser();

    ArrayList<User> getAllUser(Function<User, Boolean> condition);

    void changeUserInfo(User newUser);

}

