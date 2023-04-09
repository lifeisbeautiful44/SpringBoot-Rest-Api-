package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	/*
	 * JpaRepository is the combination of CurdRepositoryInterface and the
	 * PagingAndSortingRepository
	 * 
	 * For each table we need to have one entity class, and for each entity class ,
	 * we need to have one repository interface.
	 */

	List<Student> findByFirstName(String firstName);

	List<Student> findByFirstNameAndLastName(String firstName, String lastName);

	List<Student> findByFirstNameOrLastName(String firstName, String lastName);

	List<Student> findByFirstNameIn(List<String> firstName);

	List<Student> findByFirstNameContains(String search);

	List<Student> findByFirstNameStartsWith(String search);

	// Using JPQl query :
	@Query("From Student where firstName = :firstname and lastName = :lastName")
	List<Student> getByFirstNameOrLastName(@Param("firstname") String firstName, String lastName);
	// if the jpql variable and the method variable doesnot match then we can simple
	// use @param

	@Modifying
	@Transactional
	@Query("Update Student set firstName = :firstName where id = :id")
	Integer updateFirstNameUsingJpql(int id, String firstName);

	@Modifying
	@Transactional
	@Query("Delete from Student where email = :email")
	Integer deleteUsingJPQL(String email);
	
	/*
	 * List<Student> findByAddressCity(String city);
	 *  Same By using the JPQL
	 */
	
	@Query("From Student where address.city = :city ")
	List<Student> getByCity(String city);

}
