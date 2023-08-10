package com.example.quizwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quizwebapp.Question;
import com.example.quizwebapp.dao.QuestionDAO;

@Service
public class QuestionService 
{
	@Autowired
	QuestionDAO qDAO;
	
	// This method will fetch the data from DAO layer
	public List<Question> getAllQuestions() 
	{
		/*
		 * findAll() is a part of the interface ListCrudRepository 
		 * (which is further inherited by JpaRepository interface), 
		 * and will return a list of instances of the class Question, 
		 * since that's the class we have mentioned in the QuestionDAO Interface
		 * */
		return qDAO.findAll();
	}
	
	//----------------------------------------------------------------------------
	
	public List<Question> getQuestionsByCategory(String category) 
	{
		/*
		 * findByCategory() is a custom method, created it in the DAO layer.
		 * This is tell Spring we want the values based on the "category" column in our DB.
		 * */
		return qDAO.findByCategory(category);
	}
	
	//----------------------------------------------------------------------------
	
	public void addQuestion(Question question) 
	{
		/*
		 * For adding a question in the DB, we are going to use the save(),
		 * which is present in CrudRepository interface and is being "implemented" by JPARepository interface.
		 */
		qDAO.save(question);	
	}
	
}
