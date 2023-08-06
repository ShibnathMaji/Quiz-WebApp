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
	
}
