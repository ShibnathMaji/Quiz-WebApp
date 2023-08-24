package com.example.quizwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizwebapp.model.QuestionWrapper;
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
	
	//----------------------------------------------------------------------------	
	
	/* For accepting requests in the path-> localhost:8080/quiz/get/<preferred_id>
	 * Here, we are accepting the QuizID associated with the QuizTitle in the DB from the user.
	 * Templated part of the URL gets mapped to the Integer id.
	 * Returns the list of questions in the Quiz.
	 * It only returns the Question ID, Question Title and the Options 1-4 for each of the questions. 
	 */
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByQuizTitle(@PathVariable ("id") Integer id)
	{
		return quizService.getQuizQuestions(id);
	}
	
}
