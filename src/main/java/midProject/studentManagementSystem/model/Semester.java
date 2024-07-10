package midProject.studentManagementSystem.model;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class Semester {

	@Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String semester_name;
    
    @Column(nullable = false)
    private String start_date;
    
    @Column(nullable = false)
    private String end_date;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSemester_name() {
		return semester_name;
	}

	public void setSemester_name(String semester_name) {
		this.semester_name = semester_name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
    
    
}
