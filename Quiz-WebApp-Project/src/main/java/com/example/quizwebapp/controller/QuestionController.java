package com.example.quizwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	/* Prints All Questions Available
	 * Accepting requests in the path-> localhost:8080/Questions/allQuestions
	 * 
	 * When user asks for all the questions in the DB, we have to return a List of all the questions.
	 * There's a Entity/Model class -> Question that stores all the data, so we will return a List of objects
	 */
	@GetMapping("allQuestions")		
	public List<Question> getAllQuestions()
	{
		return qService.getAllQuestions();
	}
	
	//----------------------------------------------------------------------------
	
	/* Prints All Questions Avaialble for a certain category.
	 * Accepting requests in the path-> localhost:8080/Questions/category/<preferred_category>
	 * 
	 * This method returns list of questions available for a particular category.
	 * Accepts a String as input (category).
	 * @PathVariable("category") extracts the templated part of the URL i.e., {category} and
	 * maps it to the input variable String categoy.  
	 * Since the templated part and the variable name is same, we can use 
	 * (@PathVariable String category) instead of (@PathVariable("category") String category)
	 */
	@GetMapping("category/{category}")
	public List<Question> getQuestionByCategory( @PathVariable("category") String category)
	{
		return qService.getQuestionsByCategory(category);
	}
	
	//----------------------------------------------------------------------------
	
	/* Adds Questions
	 * Accepting requests in the path-> localhost:8080/Questions/add
	 * 
	 * This method allows a user to submit a which it then adds in the DB and returns a String value.
	 * Accepts a JSON format input from the user.
	 * @RequestBody allows for the automatic deserialization of the HttpRequest body and maps it 
	 * to the class object i.e., (Question question)
	 */
	@PostMapping("add")
	public String addQuestion(@RequestBody Question question)
	{
		qService.addQuestion(question);
		return "success";
	}
}