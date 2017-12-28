package student.session.system.form;

import student.session.system.user.UserType;

public class UserForm
{
    private String userName;
    private String userPassword;
    private String personName;
    private UserType userIdentity;
    private String userAddress;
    private String userIntroduction;

    public String getUserAddress()
    {
        return userAddress;
    }

    public void setUserAddress(String userAddress)
    {
        this.userAddress = userAddress;
    }

    public String getUserIntroduction()
    {
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction)
    {
        this.userIntroduction = userIntroduction;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public UserType getUserIdentity()
    {
        return userIdentity;
    }

    public void setUserIdentity(UserType userIdentity)
    {
        this.userIdentity = userIdentity;
    }

    public String getPersonName()
    {
        return personName;
    }

    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

}
