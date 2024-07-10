package midProject.studentManagementSystem.manage;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import midProject.studentManagementSystem.config.HibernateUtil;
import midProject.studentManagementSystem.model.Semester;

public class SemesterManager {

	public Semester saveSemester(String semesterName, String startDate, String endDate) {
        Transaction transaction = null;
        Semester semester = new Semester();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            semester.setSemester_name(semesterName);
            semester.setStart_date(startDate);
            semester.setEnd_date(endDate);
            session.save(semester);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return semester;
    }
	
	public Semester getSemesterById(UUID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Semester.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
