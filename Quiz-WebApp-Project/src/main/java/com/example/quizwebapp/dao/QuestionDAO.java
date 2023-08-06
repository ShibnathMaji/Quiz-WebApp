package com.example.quizwebapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quizwebapp.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer>  
{
	/*
	 * Question -> Name of the Entity/Model Class that maps to the DB table
	 * Integer -> Variable type of Primary key of the model class
	 */ 
	
	
	
}