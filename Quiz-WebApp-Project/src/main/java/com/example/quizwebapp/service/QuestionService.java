package com.example.quizwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizwebapp.dao.QuestionDAO;
import com.example.quizwebapp.model.Question;

@Service
public class QuestionService 
{
	@Autowired
	QuestionDAO questionDAO;
	
	// This method will fetch the data from DAO layer
	public ResponseEntity<List<Question>> getAllQuestions() 
	{
		/*
		 * findAll() is a part of the interface ListCrudRepository 
		 * (which is further inherited by JpaRepository interface), 
		 * and will return a list of instances of the class Question, 
		 * since that's the class we have mentioned in the QuestionDAO Interface
		 * */
		try
		{
			// If the request sent by user is correct, we will return Status Code OK.
			return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK) ;
		}
		catch(Exception e)
		{
			// printStackTrace() can pin-point exactly which line is causing the error to appear.
			e.printStackTrace();
		}
		// If the request sent by user is incorrect, we will return Status Code Bad Request. 
		return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.BAD_REQUEST) ;
	}
	
	//----------------------------------------------------------------------------
	
	public List<Question> getQuestionsByCategory(String category) 
	{
		/*
		 * findByCategory() is a custom method, created it in the DAO layer.
		 * This is tell Spring we want the values based on the "category" column in our DB.
		 * */
		return questionDAO.findByCategory(category);
	}
	
	//----------------------------------------------------------------------------
	
	public ResponseEntity<String> addQuestion(Question question) 
	{
		/*
		 * For adding a question in the DB, we are going to use the save(),
		 * which is present in CrudRepository interface and is being "implemented" by JPARepository interface.
		 */
		try
		{
			questionDAO.save(question);
			return new ResponseEntity<>("success", HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
	}
}
