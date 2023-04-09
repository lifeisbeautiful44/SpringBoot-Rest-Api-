package com.example.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.request.CreateStudentRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "student")
/*
 * Your class, that will represent the table in the DB, is called entity class
 */
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	@Transient
	private String fullName;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	
	@OneToMany(mappedBy = "student")
	private List<Subject> learningSubject;
	
	
	public  Student(CreateStudentRequest createStudentRequest)
	{
		this.firstName = createStudentRequest.getFirstName();
		this.lastName = createStudentRequest.getLastName();
		this.email = createStudentRequest.getEmail();
		this.fullName = createStudentRequest.getFirstName() + " " + createStudentRequest.getLastName();
		
		
	}

}
