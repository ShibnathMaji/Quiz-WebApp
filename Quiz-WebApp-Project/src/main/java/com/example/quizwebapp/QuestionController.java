package com.example.quizwebapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class accepts the questions
@RestController
@RequestMapping("Questions")  		// For accepting requests in the path-> localhost:8080/Questions
public class QuestionController 
{
	@GetMapping("allQuestions")		// For accepting requests in the path-> localhost:8080/Questions/allQuestions
	public String getAllQuestions()
	{
		return "This is getAllQuestions(). Need to insert sample return text later";	
	}
}
