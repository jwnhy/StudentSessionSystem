package student.session.basic.database;

import student.session.system.user.Student;
import student.session.system.user.Teacher;

import java.util.ArrayList;

public interface TeacherStudentDAO
{
    public ArrayList<Student> getAllStudent(Teacher teacher);

    public ArrayList<Teacher> getAllTeacher(Student student);

    public void deleteStudent(Teacher teacher, Student student);

    public void setStudent(Teacher teacher, Student student);

    public int getViolatedTimes(Teacher teacher, Student student);

    public int getUserUsedTime(Teacher teacher, Student student);

    public int getUserTimes(Teacher teacher, Student student);

    public void incViolatedTime(Teacher teacher, Student student);

    public void incUserUsedTime(Teacher teacher, Student student, int time);

    public void incUserTimes(Teacher teacher, Student student);
}
