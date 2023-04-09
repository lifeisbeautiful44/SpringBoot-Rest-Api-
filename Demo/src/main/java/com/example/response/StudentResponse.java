package com.example.response;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Student;
import com.example.entity.Subject;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//@Data , equivalent to => Getter, Setter, ToString and EqualsandHashCode.
public class StudentResponse {

	private int id;

	// @Value("${app.name}")
	@JsonProperty("first_name")
	private String firstName;

	private String lastName;
	
	private String email;
	
	private String fullName;
	
	private String street;
	
	private String city;
	
	private List<SubjectResponse> learningSubjects;
	
	
	public StudentResponse( Student student)
	{
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
		this.street = student.getAddress().getStreet();
		this.city = student.getAddress().getCity();
		this.fullName = student.getFirstName() + " "	+ student.getLastName();
		
		if(student.getLearningSubject() != null)
		{
			 learningSubjects = new ArrayList<>();
			 
			 for (Subject subject : student.getLearningSubject()) {
				learningSubjects.add(new SubjectResponse(subject));
				 
			}
		}
		
	}
	
	

	/*
	 * public StudentResponse(Long id, String firstName, String lastName) { this.id
	 * = id; this.firstName = firstName; this.lastName = lastName; }
	 * 
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getFirstName() { return firstName; }
	 * 
	 * public void setFirstName(String firstName) { this.firstName = firstName; }
	 * 
	 * public String getLastName() { return lastName; }
	 * 
	 * public void setLastName(String lastName) { this.lastName = lastName; }
	 */
}
