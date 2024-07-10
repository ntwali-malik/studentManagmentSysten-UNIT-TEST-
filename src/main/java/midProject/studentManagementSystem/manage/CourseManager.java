package midProject.studentManagementSystem.manage;

import org.hibernate.Session;
import org.hibernate.Transaction;

import midProject.studentManagementSystem.config.HibernateUtil;
import midProject.studentManagementSystem.model.Course;
import midProject.studentManagementSystem.model.Semester;

public class CourseManager {

	public Course saveCourse(String courseCode, String courseName, Semester semester) {
        Transaction transaction = null;
        Course course = new Course();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            course.setCourse_code(courseCode);
            course.setCourse_name(courseName);
            course.setSemester(semester);
            session.save(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return course;
    }
}
