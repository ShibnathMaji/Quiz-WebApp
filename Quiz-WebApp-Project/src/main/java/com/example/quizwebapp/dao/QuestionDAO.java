package com.example.quizwebapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quizwebapp.model.Question;

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
	
	//----------------------------------------------------------------------------		
	
	/* We want a specific number of questions, from a specific category and in a randomized order.
	 * DataJPA can't give us that by default. 
	 * We have to write custom queries for that. Here we'll be writing JPQL query (nativeQuery).
	 * 
	 * SELECT * FROM question_copy_table q				-> Selecting the table and representing it by q.
	 * WHERE q.category=:category ORDER BY RANDOM()		-> Randomizing the questions getting picked from the 
	 * 													   mentioned category. The column in the table is also
	 * 													   called category.
	 * LIMIT :numQ										-> Limiting how many queries get picked.
	 */
	@Query(value = "SELECT * FROM question_copy_table q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", 
			nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int numQ);
	
	
	
}