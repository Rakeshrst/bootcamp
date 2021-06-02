package com.rst.bootcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rst.bootcamp.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer>{

}
