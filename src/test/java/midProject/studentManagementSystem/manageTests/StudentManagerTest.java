package midProject.studentManagementSystem.manageTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import midProject.studentManagementSystem.manage.StudentManager;
import midProject.studentManagementSystem.model.Student;

public class StudentManagerTest {

	private StudentManager studentManager;

    @BeforeAll
    void setUp() {
        studentManager = new StudentManager();
    }
    
    @Test
    public void testSaveAndRetrieveStudent() {
        String firstName = "Kebean" + UUID.randomUUID().toString().substring(0, 8);
        String lastName = "Elie" + UUID.randomUUID().toString().substring(0, 8);
        String dateOfBirth = "2000-01-01";
        String gender = "Male";

        studentManager.saveStudent(dateOfBirth, firstName, lastName, gender);

        Student retrievedStudent = studentManager.getByFirstNameAndLastName(firstName, lastName);

        assertNotNull("Retrieved student should not be null", retrievedStudent);
        assertEquals("First name should match", firstName, retrievedStudent.getFirst_name());
        assertEquals("Last name should match", lastName, retrievedStudent.getLast_name());
        assertEquals("Date of birth should match", dateOfBirth, retrievedStudent.getDate_of_birth());
        assertEquals("Gender should match", gender, retrievedStudent.getGender());
    }
    
    @Test
    public void testGetByFirstNameAndLastNameNonExistent() {
        String firstName = "NonExistent" + UUID.randomUUID().toString().substring(0, 8);
        String lastName = "Person" + UUID.randomUUID().toString().substring(0, 8);

        Student nonExistentStudent = studentManager.getByFirstNameAndLastName(firstName, lastName);
        assertNull("Non-existent student should return null", nonExistentStudent);
    }
}
