package midProject.studentManagementSystem.manage;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import midProject.studentManagementSystem.config.HibernateUtil;
import midProject.studentManagementSystem.model.Department;
import midProject.studentManagementSystem.model.Semester;
import midProject.studentManagementSystem.model.Student;
import midProject.studentManagementSystem.model.StudentRegistration;

public class StudentRegistrationManager {

    public StudentRegistration registerStudent(UUID studentId, UUID semesterId, UUID departmentId) {
        Transaction transaction = null;
        StudentRegistration studentRegistration = new StudentRegistration();
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            Student student = session.get(Student.class, studentId);
            Semester semester = session.get(Semester.class, semesterId);
            Department department = session.get(Department.class, departmentId);
            
            if (student == null || semester == null || department == null) {
                throw new IllegalArgumentException("Invalid student, semester, or department ID.");
            }

            studentRegistration.setStudent(student);
            studentRegistration.setSemester(semester);
            studentRegistration.setDepartment(department);
            
            session.save(studentRegistration);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return studentRegistration;
    }
}