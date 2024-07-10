package midProject.studentManagementSystem.manage;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import midProject.studentManagementSystem.config.HibernateUtil;
import midProject.studentManagementSystem.model.Department;
import midProject.studentManagementSystem.model.Semester;
import midProject.studentManagementSystem.model.Student;

public class StudentManager {

    public Student saveStudent(String dateOfBirth, String firstName, String lastName, String gender) {
        Transaction transaction = null;
        Student student = new Student();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            student.setDate_of_birth(dateOfBirth);
            student.setFirst_name(firstName);
            student.setLast_name(lastName);
            student.setGender(gender);
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }
    
    public Student getByFirstNameAndLastName(String firstName, String lastName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Student> students = session.createQuery(
                    "FROM Student WHERE first_name = :firstName AND last_name = :lastName", Student.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .list();
            if (students != null && !students.isEmpty()) {
                return students.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}