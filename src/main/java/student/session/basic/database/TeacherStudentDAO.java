package student.session.basic.database;

import student.session.system.user.Student;
import student.session.system.user.Teacher;

import java.util.ArrayList;

public interface TeacherStudentDAO
{
    ArrayList<Student> getAllStudent(Teacher teacher);

    ArrayList<Teacher> getAllTeacher(Student student);

    void deleteStudent(Teacher teacher, Student student);

    void setStudent(Teacher teacher, Student student);

    int getViolatedTimes(Teacher teacher, Student student);

    int getUserUsedTime(Teacher teacher, Student student);

    int getUserTimes(Teacher teacher, Student student);

    void incViolatedTime(Teacher teacher, Student student);

    void incUserUsedTime(Teacher teacher, Student student, int time);

    void incUserTimes(Teacher teacher, Student student,int times);

    void monthlyClean();

}
