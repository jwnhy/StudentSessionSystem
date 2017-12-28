package student.session.basic.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import student.session.system.user.Student;
import student.session.system.user.Teacher;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class JdbcTeacherStudentDAO implements TeacherStudentDAO
{
    @Autowired
    DataSource dataSource;
    @Autowired
    UserDAO userDAO;

    @Override
    public ArrayList<Student> getAllStudent(Teacher teacher)
    {
        Connection connection = null;
        String sql = "SELECT * FROM teacherStudentTable WHERE teacherName = ?";
        ArrayList<Student> resList = new ArrayList<Student>();
        try
        {
            ResultSet res = null;
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, teacher.getUserName());
            res = sqlStatement.executeQuery();
            while (res.next())
                resList.add((Student) userDAO.findByUserName(res.getString("studentName")));
            return resList;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
            }
        }
    }

    @Override
    public ArrayList<Teacher> getAllTeacher(Student student)
    {
        Connection connection = null;
        String sql = "SELECT * FROM teacherStudentTable WHERE studentName = ?";
        ArrayList<Teacher> resList = new ArrayList<Teacher>();
        try
        {
            ResultSet res = null;
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, student.getUserName());
            res = sqlStatement.executeQuery();
            while (res.next())
                resList.add((Teacher) userDAO.findByUserName(res.getString("teacherName")));
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
            }
        }
        return resList;
    }


    @Override
    public void deleteStudent(Teacher teacher, Student student)
    {
        Connection connection = null;
        String sql = "DELETE FORM teacherStudentTable WHERE studentName = ? AND teacherName = ?";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, student.getUserName());
            sqlStatement.setString(2, teacher.getUserName());
            sqlStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
            }
        }
    }

    @Override
    public void setStudent(Teacher teacher, Student student)
    {
        Connection connection = null;
        String sql = "INSERT INTO teacherStudentTable " +
                "(teacherName, studentName)" + "VALUES (?,?)";
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, teacher.getUserName());
            sqlStatement.setString(2, student.getUserName());
            sqlStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connection != null)
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
        }
    }

    public int getViolatedTimes(Teacher teacher, Student student)
    {
        String sql = "SELECT * FROM teacherStudentTable " +
                "WHERE teacherName = ? AND studentName = ?";
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, teacher.getUserName());
            sqlStatement.setString(2, student.getUserName());
            ResultSet res = sqlStatement.executeQuery();
            if (res.next())
                return res.getInt("violatedTimes");
            return -1;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connection != null)
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
        }
    }

    public int getUserUsedTime(Teacher teacher, Student student)
    {
        String sql = "SELECT * FROM teacherStudentTable " +
                "WHERE teacherName = ? AND studentName = ?";
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, teacher.getUserName());
            sqlStatement.setString(2, student.getUserName());
            ResultSet res = sqlStatement.executeQuery();
            if (res.next())
                return res.getInt("userUsedTime");
            return -1;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connection != null)
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
        }
    }

    public int getUserTimes(Teacher teacher, Student student)
    {
        String sql = "SELECT * FROM teacherStudentTable " +
                "WHERE teacherName = ? AND studentName = ?";
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, teacher.getUserName());
            sqlStatement.setString(2, student.getUserName());
            ResultSet res = sqlStatement.executeQuery();
            if (res.next())
                return res.getInt("userTimes");
            return -1;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connection != null)
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
        }
    }

    public void incViolatedTime(Teacher teacher, Student student)
    {
        String sql = "UPDATE teacherStudentTable SET violatedTimes = violatedTimes + 1 WHERE teacherName = ? AND studentName = ?";
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, teacher.getUserName());
            sqlStatement.setString(2, student.getUserName());
            sqlStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connection != null)
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
        }
    }

    public void incUserUsedTime(Teacher teacher, Student student, int time)
    {
        String sql = "UPDATE teacherStudentTable SET userUsedTime = userUsedTime + " + time
                + " WHERE teacherName = ? AND studentName = ?";
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, teacher.getUserName());
            sqlStatement.setString(2, student.getUserName());
            sqlStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connection != null)
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
        }
    }

    public void incUserTimes(Teacher teacher, Student student)
    {
        String sql = "UPDATE teacherStudentTable SET userTimes = userTimes + 1 WHERE teacherName = ? AND studentName = ?";
        Connection connection = null;
        try
        {
            connection = dataSource.getConnection();
            PreparedStatement sqlStatement = connection.prepareStatement(sql);
            sqlStatement.setString(1, teacher.getUserName());
            sqlStatement.setString(2, student.getUserName());
            sqlStatement.execute();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connection != null)
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                }
        }
    }
}
