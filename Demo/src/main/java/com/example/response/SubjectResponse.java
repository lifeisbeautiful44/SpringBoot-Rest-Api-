package com.example.response;

import com.example.entity.Subject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubjectResponse {
	
	private int id;
	
	private String subject_name;
	
	private double marks_obtained;
	
	
	public SubjectResponse(Subject subject)
	{
		
		this.id = subject.getId();
		this.subject_name = subject.getSubject_name();
		this.marks_obtained = subject.getMarks_obtained();
	}
	

}
