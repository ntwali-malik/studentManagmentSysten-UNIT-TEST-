package midProject.studentManagementSystem.manage;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import midProject.studentManagementSystem.config.HibernateUtil;
import midProject.studentManagementSystem.model.Course;
import midProject.studentManagementSystem.model.StudentCourses;
import midProject.studentManagementSystem.model.StudentRegistration;

public class StudentCoursesManager {

    public StudentCourses saveStudentCourseMarks(UUID courseId, UUID registrationId, int marks) {
        Transaction transaction = null;
        StudentCourses studentCourses = new StudentCourses();
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            Course course = session.get(Course.class, courseId);
            StudentRegistration registration = session.get(StudentRegistration.class, registrationId);
            
            if (course == null || registration == null) {
                throw new IllegalArgumentException("Invalid course or registration ID.");
            }
            
            studentCourses.setCourse(course);
            studentCourses.setRegistration(registration);
            studentCourses.setMarks_in_course(marks);
            
            session.save(studentCourses);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return studentCourses;
    }
    public int getTotalMarksInSemester(UUID registrationId) {
        int totalMarks = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<StudentCourses> studentCourses = session.createQuery(
                    "FROM StudentCourses WHERE registration.id = :registrationId", StudentCourses.class)
                    .setParameter("registrationId", registrationId)
                    .list();

            for (StudentCourses course : studentCourses) {
                totalMarks += course.getMarks_in_course();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Assuming each course is graded on 20 marks, and the total is calculated on 100 marks
        return totalMarks * 5; // Multiply by 5 to convert from 20 marks per course to 100 marks total
    }
    
    public int getTotalMarksInSemester1(UUID registrationId) {
        int totalMarks = 0;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<StudentCourses> studentCourses = session.createQuery(
                    "FROM StudentCourses WHERE registration.id = :registrationId", StudentCourses.class)
                    .setParameter("registrationId", registrationId)
                    .list();

            for (StudentCourses course : studentCourses) {
                totalMarks += course.getMarks_in_course();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Assuming each course is graded on 20 marks, and the total is calculated on 100 marks
        return totalMarks * 5; // Multiply by 5 to convert from 20 marks per course to 100 marks total
    }

    public int convertTo20MarksScale(int totalMarks) {
        // Convert from 100 marks scale to 20 marks scale
        return totalMarks / 5;
    }
    public String evaluateGrade(int marksOn20Scale) {
        if (marksOn20Scale >= 16) {
            return "High Distinction";
        } else if (marksOn20Scale >= 12 && marksOn20Scale < 16) {
            return "Lower Distinction";
        } else if (marksOn20Scale >= 10 && marksOn20Scale < 12) {
            return "Pass";
        } else {
            return "Expel";
        }
    }
}
