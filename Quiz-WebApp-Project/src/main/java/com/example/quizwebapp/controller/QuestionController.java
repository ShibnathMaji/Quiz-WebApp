package com.example.quizwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizwebapp.service.QuestionService;

// This class accepts the questions

// We are building a RESTful Web Application, hence we'll be using @RestController annotation
@RestController
//For accepting requests in the path-> localhost:8080/Questions
@RequestMapping("Questions")  		
public class QuestionController 
{
	/* Creating an object of the QuestionService class.
	 * Being in Spring Framework, we don't need to use the "new" keyword.
	 * @Autowired annotation does it for us 
	 */
	@Autowired
	QuestionService qService;
	
	// For accepting requests in the path-> localhost:8080/Questions/allQuestions
	@GetMapping("allQuestions")		
	public String getAllQuestions()
	{
		return "This is getAllQuestions(). Need to insert sample return text later";	
	}
}