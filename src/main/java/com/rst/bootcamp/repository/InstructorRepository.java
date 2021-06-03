package com.rst.bootcamp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rst.bootcamp.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer>{
	
	@Query(value = "select n from Instructor n inner join n.parent p where p.id = :parentId")
	List<Instructor> findInstructorByParentId(@Param("parentId") int parentId);
	

	Instructor findByInstructorName(String instructorName);
}
