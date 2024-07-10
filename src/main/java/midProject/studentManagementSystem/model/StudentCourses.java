package midProject.studentManagementSystem.model;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class StudentCourses {

	@Id
    @GeneratedValue
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "course_code", nullable = false)
    private Course course;
    
    @ManyToOne
    @JoinColumn(name = "registration_number", nullable = false)
    private StudentRegistration registration;
    
    @Column(nullable = false)
    private int marks_in_course;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public StudentRegistration getRegistration() {
		return registration;
	}

	public void setRegistration(StudentRegistration registration) {
		this.registration = registration;
	}

	public int getMarks_in_course() {
		return marks_in_course;
	}

	public void setMarks_in_course(int marks_in_course) {
		this.marks_in_course = marks_in_course;
	}
    
    
}
