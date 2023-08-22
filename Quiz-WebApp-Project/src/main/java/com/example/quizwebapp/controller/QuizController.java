package com.example.quizwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.quizwebapp.service.QuizService;

@RestController
//For accepting requests in the path-> localhost:8080/quiz
@RequestMapping("quiz")
public class QuizController 
{
	@Autowired
	QuizService quizService;
	
	/* For accepting requests in the path-> localhost:8080/quiz/create
	 * This method allows users to create a quiz.
	 * We'll take three parameters as input from the request URL: 
	 * 		1. String category: Category of questions.
	 * 		2. int numQ :       Number of questions in the quiz
	 *  	3. String title:    The name/title of the quiz created 
	 *  The URL syntax is:
	 *  localhost:8080/quiz/create?category=<category>&numQ=<numQ>&title=<title>
	 *  
	 *  @RequestParam annotation extracts the parameters from the URL and maps it to the respective
	 *  input variables 
	 */
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, 
			@RequestParam int numQ, @RequestParam String title)
	{
		return quizService.createQuiz(category, numQ, title);
	}
}
