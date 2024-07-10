package midProject.studentManagementSystem.manage;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import midProject.studentManagementSystem.config.HibernateUtil;
import midProject.studentManagementSystem.model.Department;

public class DepartmentManager {

    public Department saveDepartment(String departmentCode, String departmentName) {
        Transaction transaction = null;
        Department department = new Department();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            department.setDepartment_code(departmentCode);
            department.setDepartment_name(departmentName);
            session.save(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return department;
    }

    public Department getDepartmentById(UUID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Department.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
