package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

	@Value("${app.name:Default Demo App}")
	private String appName;

	/*
	 * @GetMapping("/get") // @RequestMapping(name = "/get", method =
	 * RequestMethod.GET) public StudentResponse getStudent() { StudentResponse
	 * studentResponse = new StudentResponse(1l, "Srijansil", "Bohara"); return
	 * studentResponse; }
	 */

	// error < warn < info < debug < trace
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	StudentService studentService;

	/*
	 * Here We return our entity class , our entity class is nothing but the
	 * Representing table of the DB, so we should not expose our DB to the
	 * consumers.
	 */

	@GetMapping("/get")
	public List<Student> getStudent() {
		return studentService.findAllStudents();
	}

	@GetMapping("/getAll")
	public List<StudentResponse> getStudents() {
		logger.error("Inside Error.");
		logger.warn("Inside Warning.");
		logger.info("Inside Info.");
		logger.debug("Inside Debug.");
		logger.trace("Inside Trace.");

		List<Student> studentList = studentService.findAllStudents();
		List<StudentResponse> studentResponseList = new ArrayList<>();

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		/*
		 * It will convert each and every student object into , into StudentResponse ,
		 * and add too studentResponseList.
		 */
		return studentResponseList;

	}

	@PostMapping("/create")
	public StudentResponse createStudents(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
		// System.out.println(createStudentRequest);
		Student student = studentService.createStudent(createStudentRequest);

		return new StudentResponse(student);

	}

	@PutMapping("/update")
	public StudentResponse updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest) {

		Student updateStudent = studentService.updateStudent(updateStudentRequest);

		return new StudentResponse(updateStudent);
	}

	/*
	 * By using Request Param
	 * 
	 * @DeleteMapping("/delete") public String deleteSetudent(@RequestParam int id)
	 * { return studentService.deleteStudent(id); }
	 */

	@DeleteMapping("/delete/{id}")
	public String deleteSetudent(@PathVariable int id) {
		return studentService.deleteStudent(id);
	}

	// using where clause
	@GetMapping("/getByFirstName/{firstName}")
	public List<StudentResponse> getByFirstName(@PathVariable String firstName) {
		List<Student> byFirstName = studentService.getByFirstName(firstName);
		List<StudentResponse> studentResponse = new ArrayList<>();

		byFirstName.stream().forEach(studentByFirstName -> {
			studentResponse.add(new StudentResponse(studentByFirstName));
		});

		return studentResponse;
	}

	// Select * from student where firstName = '' AND lastName ='';

	@GetMapping("/getByfirstNameAndLastName/{firstName}/{lastName}")
	public List<StudentResponse> getByfirstNameAndLastName(@PathVariable String firstName,
			@PathVariable String lastName) {

		List<Student> byfirstNameAndLastName = studentService.getByfirstNameAndLastName(firstName, lastName);

		System.out.println(byfirstNameAndLastName);
		List<StudentResponse> studentResponse = new ArrayList<>();

		byfirstNameAndLastName.stream().forEach(student -> {
			studentResponse.add(new StudentResponse(student));
		});

		return studentResponse;

	}

	// Select * from Student where firstName = '' 0R lastName = ''
	@GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
	public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName,
			@PathVariable String lastName) {

		List<Student> byfirstNameOrLastName = studentService.getByfirstNameOrLastName(firstName, lastName);

		List<StudentResponse> studentResponse = new ArrayList<>();

		byfirstNameOrLastName.stream().forEach(studentByFirstNameOrLastName -> {
			studentResponse.add(new StudentResponse(studentByFirstNameOrLastName));

		});

		return studentResponse;

	}

	// select * from student where first_name in ('rabin','kabin');
	@GetMapping("/getByFirstNameIn")
	public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
		//logger.info("InQueryRequest= " + inQueryRequest);

		List<Student> byFirstNameIn = studentService.getByFirstNameIn(inQueryRequest);
		List<StudentResponse> studentResponse = new ArrayList<>();

		byFirstNameIn.stream().forEach(studentWithINQuery -> {
			studentResponse.add(new StudentResponse(studentWithINQuery));
		});

		logger.info("StudentResponse= " + studentResponse);
		return studentResponse;

	}

	// select * from student limit 5 offset 15;
	@GetMapping("/getAllStudentsWithPagiantion")
	public List<StudentResponse> getAllStudentsWithPagiantion(@RequestParam int page_no, int page_size) {
		List<Student> allStudentsWithPagiantion = studentService.getAllStudentsWithPagiantion(page_no, page_size);
		List<StudentResponse> studentResponse = new ArrayList<>();

		allStudentsWithPagiantion.stream().forEach(studentsWithPagination -> {
			studentResponse.add(new StudentResponse(studentsWithPagination));
		});

		return studentResponse;

	}

	// select * from student order by first_name, last_name asc;
	@GetMapping("/getAllWithSorting")
	public List<StudentResponse> getAllStudentsWithSorting() {
		List<Student> student = studentService.getAllStudentsWithSorting();
		List<StudentResponse> studentResponse = new ArrayList<>();

		student.stream().forEach(studentWithSort -> {
			studentResponse.add(new StudentResponse(studentWithSort));
		});

		return studentResponse;
	}

	// select * from student where first_name like '%ar%';
	@GetMapping("/likeQuery/{search}")
	public List<StudentResponse> likeQuery(@PathVariable String search) {

		List<Student> student = studentService.likeQuery(search);
		List<StudentResponse> studentResponse = new ArrayList<>();

		student.stream().forEach(studentWithLike -> {
			studentResponse.add(new StudentResponse(studentWithLike));
		});

		return studentResponse;
	}

	// select * from student where first_name like 'sri%';
	@GetMapping("/startsWith/{search}")
	public List<StudentResponse> startsWith(@PathVariable String search) {

		List<Student> student = studentService.startsWith(search);
		List<StudentResponse> studentResponse = new ArrayList<>();

		student.stream().forEach(studentStartsWith -> {
			studentResponse.add(new StudentResponse(studentStartsWith));
		});

		return studentResponse;
	}

	// Select query using jpql, in the firstname
	@GetMapping("/getStudentByFirstNameAndLastName/{firstName}/{lastName}")
	public List<StudentResponse> selectQueryQithJPQl(@PathVariable String firstName, @PathVariable String lastName) {

		List<Student> student = studentService.selectQueryQithJPQl(firstName, lastName);

		List<StudentResponse> studentResponse = new ArrayList<>();

		student.stream().forEach(selectQueryQithJPQl -> {
			studentResponse.add(new StudentResponse(selectQueryQithJPQl));
		});

		return studentResponse;
	}

	// Update query with jpql
	@PutMapping("/updateWithJpql/{id}/{firstName}")
	public String updateWithJpql(@PathVariable int id, @PathVariable String firstName) {

		Integer updateWithJpql = studentService.updateWithJpql(id, firstName);

		return updateWithJpql + " Student(s) is being  updated";
	}

	// Delete query with jpql
	@DeleteMapping("/updateWithJpql/{email}")
	public String deleteWithJpql(@PathVariable String email) {

		Integer deleteWithJpql = studentService.deleteWithJpql(email);

		return deleteWithJpql + " Student(s) is being  deleted";
	}

// select * from student s inner join address a on s.address_id = a.id where a.city = 'Ktm';
	@GetMapping("/getByCity/{city}")
	public List<StudentResponse> getByCity(@PathVariable String city) {

		List<Student> student = studentService.getByCity(city);

		List<StudentResponse> studentResponse = new ArrayList<>();

		student.stream().forEach(byCity -> {
			studentResponse.add(new StudentResponse(byCity));
		});

		return studentResponse;

	}

}
