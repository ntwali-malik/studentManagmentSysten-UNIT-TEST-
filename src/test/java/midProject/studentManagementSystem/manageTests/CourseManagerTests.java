package midProject.studentManagementSystem.manageTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import midProject.studentManagementSystem.manage.CourseManager;
import midProject.studentManagementSystem.manage.SemesterManager;
import midProject.studentManagementSystem.model.Course;
import midProject.studentManagementSystem.model.Semester;

public class CourseManagerTests {

	private CourseManager courseManager;
    private Semester testSemester;
    
    @Before
    public void setUp() {
        courseManager = new CourseManager();
        testSemester = new Semester();
        testSemester.setSemester_name("Spring 2024");
        testSemester.setStart_date("2024-01-15");
        testSemester.setEnd_date("2024-05-15");

        // Assuming that saveSemester method is available in SemesterManager
        SemesterManager semesterManager = new SemesterManager();
        testSemester = semesterManager.saveSemester(
            testSemester.getSemester_name(),
            testSemester.getStart_date(),
            testSemester.getEnd_date()
        );
    }
    
    @Test
    public void testSaveCourse() {
        String courseCode = "CS101";
        String courseName = "Introduction to Computer Science";

        Course savedCourse = courseManager.saveCourse(courseCode, courseName, testSemester);

        assertNotNull("Saved course should not be null", savedCourse);
        assertNotNull("Course ID should not be null", savedCourse.getId());
        assertEquals("Course code should match", courseCode, savedCourse.getCourse_code());
        assertEquals("Course name should match", courseName, savedCourse.getCourse_name());
        assertEquals("Semester should match", testSemester.getId(), savedCourse.getSemester().getId());
    }
}
