package midProject.studentManagementSystem;

import java.util.Scanner;
import java.util.UUID;

import midProject.studentManagementSystem.manage.CourseManager;
import midProject.studentManagementSystem.manage.DepartmentManager;
import midProject.studentManagementSystem.manage.SemesterManager;
import midProject.studentManagementSystem.manage.StudentCoursesManager;
import midProject.studentManagementSystem.manage.StudentManager;
import midProject.studentManagementSystem.manage.StudentRegistrationManager;
import midProject.studentManagementSystem.model.Course;
import midProject.studentManagementSystem.model.Department;
import midProject.studentManagementSystem.model.Semester;
import midProject.studentManagementSystem.model.Student;
import midProject.studentManagementSystem.model.StudentCourses;
import midProject.studentManagementSystem.model.StudentRegistration;

public class App 
{
	private static Scanner scanner = new Scanner(System.in);
    private static StudentManager studentManager = new StudentManager();
    private static SemesterManager semesterManager = new SemesterManager();
    private static CourseManager courseManager = new CourseManager();
    private static DepartmentManager departmentManager = new DepartmentManager();
    private static StudentRegistrationManager studentRegistrationManager = new StudentRegistrationManager();
    private static StudentCoursesManager studentCoursesManager = new StudentCoursesManager();
    
    public static void main(String[] args) {
        System.out.println("Welcome to the Student Management System!");

        while (true) {
        	System.out.println("\nChoose an option:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Retrieve Student by Name");
            System.out.println("4. Register Student to Semester and Department");
            System.out.println("5. Add Department");
            System.out.println("6. Save Student Course Marks");
            System.out.println("7. Calculate Total Marks in Semester");
            System.out.println("8. Calculate Total Marks in Semester On 20");
            System.out.println("9. Check Grade");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    retrieveStudentByName();
                    break;
                case 4:
                    registerStudentToSemesterAndDepartment();
                    break;
                case 5:
                    addDepartment();
                    break;
                case 6:
                    saveStudentCourseMarks();
                    break;
                case 7:
                    calculateTotalMarksInSemester();
                    break;
                case 8:
                	calculateTotalMarksInSemesterOnTwenty();
                    break;
                case 9:
                	checkGrade();
                    break;     
                case 10:
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }

    private static void addStudent() {
        System.out.println("\nEnter student details:");
        System.out.print("Date of birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        Student student = studentManager.saveStudent(dateOfBirth, firstName, lastName, gender);
        System.out.println("\nStudent saved with ID: " + student.getId());
    }

    private static void addCourse() {
        System.out.println("\nEnter course details:");
        System.out.print("Course code: ");
        String courseCode = scanner.nextLine();

        System.out.print("Course name: ");
        String courseName = scanner.nextLine();

        // Add or select semester
        Semester semester = selectOrCreateSemester();

        Course course = courseManager.saveCourse(courseCode, courseName, semester);
        System.out.println("\nCourse saved with ID: " + course.getId());
    }

    private static Semester selectOrCreateSemester() {
        System.out.println("\n1. Create New Semester");
        System.out.println("2. Select Existing Semester");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()

        switch (choice) {
            case 1:
                return createNewSemester();
            case 2:
                return selectExistingSemester();
            default:
                System.out.println("Invalid choice. Creating a new semester by default.");
                return createNewSemester();
        }
    }

    private static Semester createNewSemester() {
        System.out.print("Enter semester name: ");
        String semesterName = scanner.nextLine();

        System.out.print("Enter semester start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter semester end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        return semesterManager.saveSemester(semesterName, startDate, endDate);
    }

    private static Semester selectExistingSemester() {
        System.out.print("Enter semester ID: ");
        String semesterId = scanner.nextLine();

        try {
            // Parse UUID from string input
            UUID id = UUID.fromString(semesterId);
            Semester semester = semesterManager.getSemesterById(id);

            if (semester != null) {
                System.out.println("Selected Semester: " + semester.getSemester_name());
                return semester;
            } else {
                System.out.println("Semester with ID " + semesterId + " does not exist. Creating a new semester.");
                return createNewSemester();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format. Creating a new semester by default.");
            return createNewSemester();
        }
    }

    private static void retrieveStudentByName() {
        System.out.println("\nEnter student details to retrieve:");
        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        Student student = studentManager.getByFirstNameAndLastName(firstName, lastName);
        if (student != null) {
            System.out.println("\nStudent found:");
            System.out.println("ID: " + student.getId());
            System.out.println("Date of Birth: " + student.getDate_of_birth());
            System.out.println("First Name: " + student.getFirst_name());
            System.out.println("Last Name: " + student.getLast_name());
            System.out.println("Gender: " + student.getGender());
        } else {
            System.out.println("\nNo student found with the given name.");
        }
    }

    private static void registerStudentToSemesterAndDepartment() {
        System.out.print("\nEnter student ID: ");
        String studentIdStr = scanner.nextLine();
        UUID studentId = UUID.fromString(studentIdStr);

        System.out.print("Enter semester ID: ");
        String semesterIdStr = scanner.nextLine();
        UUID semesterId = UUID.fromString(semesterIdStr);

        System.out.print("Enter department ID: ");
        String departmentIdStr = scanner.nextLine();
        UUID departmentId = UUID.fromString(departmentIdStr);

        StudentRegistration studentRegistration = studentRegistrationManager.registerStudent(studentId, semesterId, departmentId);
        System.out.println("Student registered with ID: " + studentRegistration.getId());
    }

    private static void addDepartment() {
        System.out.println("\nEnter department details:");
        System.out.print("Department code: ");
        String departmentCode = scanner.nextLine();

        System.out.print("Department name: ");
        String departmentName = scanner.nextLine();

        Department department = departmentManager.saveDepartment(departmentCode, departmentName);
        System.out.println("\nDepartment saved with ID: " + department.getId());
    }
    private static void saveStudentCourseMarks() {
        System.out.print("\nEnter course ID: ");
        String courseIdStr = scanner.nextLine();
        UUID courseId = UUID.fromString(courseIdStr);

        System.out.print("Enter student registration ID: ");
        String registrationIdStr = scanner.nextLine();
        UUID registrationId = UUID.fromString(registrationIdStr);

        System.out.print("Enter marks in course: ");
        int marks = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()

        StudentCourses studentCourses = studentCoursesManager.saveStudentCourseMarks(courseId, registrationId, marks);
        System.out.println("Marks saved with ID: " + studentCourses.getId());
    }
    private static void calculateTotalMarksInSemester() {
        System.out.print("\nEnter student registration ID: ");
        String registrationIdStr = scanner.nextLine();
        UUID registrationId = UUID.fromString(registrationIdStr);

        int totalMarks = studentCoursesManager.getTotalMarksInSemester(registrationId);
        System.out.println("Total marks in semester (out of 100): " + totalMarks);
    }
    
    private static void calculateTotalMarksInSemesterOnTwenty() {
        System.out.print("\nEnter student registration ID: ");
        String registrationIdStr = scanner.nextLine();
        UUID registrationId = UUID.fromString(registrationIdStr);

        int totalMarks = studentCoursesManager.getTotalMarksInSemester(registrationId);
        int marksOn20Scale = studentCoursesManager.convertTo20MarksScale(totalMarks);

        System.out.println("Total marks in semester (out of 20): " + marksOn20Scale);
    }
    
    private static void checkGrade() {
        System.out.print("\nEnter student registration ID: ");
        String registrationIdStr = scanner.nextLine();
        UUID registrationId = UUID.fromString(registrationIdStr);

        int totalMarks = studentCoursesManager.getTotalMarksInSemester(registrationId);
        int marksOn20Scale = studentCoursesManager.convertTo20MarksScale(totalMarks);

        System.out.println("Total marks in semester (out of 20): " + marksOn20Scale);
        String grade = studentCoursesManager.evaluateGrade(marksOn20Scale);
        System.out.println("Grade: " + grade);
    }
}