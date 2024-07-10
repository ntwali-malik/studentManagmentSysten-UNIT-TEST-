package midProject.studentManagementSystem.model;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class Department {

	@Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String department_code;
    
    @Column(nullable = false)
    private String department_name;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
    
    
}
