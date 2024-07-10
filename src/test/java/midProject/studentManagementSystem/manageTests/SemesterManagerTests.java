package midProject.studentManagementSystem.manageTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import midProject.studentManagementSystem.manage.SemesterManager;
import midProject.studentManagementSystem.model.Semester;

public class SemesterManagerTests {

	private SemesterManager semesterManager;

    @Before
    public void setUp() {
        semesterManager = new SemesterManager();
    }

    @Test
    public void testSaveSemester() {
        String semesterName = "Software Testing";
        String startDate = "2024-01-15";
        String endDate = "2024-05-15";

        Semester savedSemester = semesterManager.saveSemester(semesterName, startDate, endDate);

        assertNotNull("Saved semester should not be null", savedSemester);
        assertNotNull("Semester ID should not be null", savedSemester.getId());
        assertEquals("Semester name should match", semesterName, savedSemester.getSemester_name());
        assertEquals("Start date should match", startDate, savedSemester.getStart_date());
        assertEquals("End date should match", endDate, savedSemester.getEnd_date());
    }
}
