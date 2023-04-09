package com.example.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateStudentRequest {
	
	@NotNull(message = "Student Id is required, to update the Student information. Please provide the student Id")
	private int id;
	
	@JsonProperty("first_Name")
	private String firstName;
	
	private String lastName;
	
	private String email;
	

}
