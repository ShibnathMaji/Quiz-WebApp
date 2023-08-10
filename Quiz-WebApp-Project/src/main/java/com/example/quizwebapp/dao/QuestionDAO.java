package com.example.quizwebapp.dao;

import java.util.List;

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
	
	//----------------------------------------------------------------------------
	
	/*
	 * Since, in our table we have a column named "category" and here our custom method name is findByCategory,
	 * DataJPA can understand that we want to fetch the values based on the the category column.
	 */
	List<Question> findByCategory(String category);
	
	
	
}