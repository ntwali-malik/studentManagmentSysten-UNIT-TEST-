package midProject.studentManagementSystem.model;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class Course {

	@Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String course_code;
    
    @Column(nullable = false)
    private String course_name;
    
    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}
}
