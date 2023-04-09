package com.example.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateStudentRequest {
	
	@JsonProperty("first_name")
	@NotBlank(message ="Firstname cannot be empty.")
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String street; 
	
	private String city;
	
	private List<CreateSubjectRequest> subjectsLearing;
	
	
	
	
	
	
	

	}
	
	
	
	


