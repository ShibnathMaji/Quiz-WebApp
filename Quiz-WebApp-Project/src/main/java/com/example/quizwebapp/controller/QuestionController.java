package com.example.quizwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizwebapp.Question;
import com.example.quizwebapp.service.QuestionService;

// This class accepts the questions

// We are building a RESTful Web Application, hence we'll be using @RestController annotation
@RestController
//For accepting requests in the path-> localhost:8080/Questions
@RequestMapping("Questions")  		
public class QuestionController 
{
	/* 
	 * Creating an object of the QuestionService class.
	 * Being in Spring Framework, we don't need to use the "new" keyword.
	 * @Autowired annotation does it for us 
	 */
	@Autowired
	QuestionService qService;
	
	/* For accepting requests in the path-> localhost:8080/Questions/allQuestions.
	 * 
	 * When user asks for all the questions in the DB, we have to return a List of all the questions.
	 * There's a Entity/Model class -> Question that stores all the data, so we will return a List of objects
	 */
	@GetMapping("allQuestions")		
	public List<Question> getAllQuestions()
	{
		return qService.getAllQuestions();
	}
}