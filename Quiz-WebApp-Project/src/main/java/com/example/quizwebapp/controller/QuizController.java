package com.example.quizwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizwebapp.model.QuestionWrapper;
import com.example.quizwebapp.model.Quiz;
import com.example.quizwebapp.model.Response;
import com.example.quizwebapp.service.QuizService;

@RestController
//For accepting requests in the path-> localhost:8080/quiz
@RequestMapping("quiz")
public class QuizController 
{
	@Autowired
	QuizService quizService;
	
	
	//---- GET/READ Operations  BEGIN -----------------------------------------------------------------------//
	
	
	
	/* Shows the list of Quizzes created
	 * ---------------------------------
	 * For accepting requests in the path-> localhost:8080/quiz/getAllQuizzes
	 * This method shows all of the Quizzes present in the DB.
	 */
	@GetMapping("getAllQuizzes")
	public ResponseEntity<List<Quiz>> getAllQuizzes()
	{
		return quizService.getAllQuizzes();
		
	}
	
	//----------------------------------------------------------------------------	
	
	/* Shows Questions in the quiz.
	 * ----------------------------
	 * For accepting requests in the path-> localhost:8080/quiz/get/<preferred_id>
	 * Here, we are accepting the QuizID associated with the QuizTitle in the DB from the user.
	 * Templated part of the URL gets mapped to the Integer id.
	 * Returns the list of questions in the Quiz.
	 * It only returns the Question ID, Question Title and the Options 1-4 for each of the questions. 
	 */
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByQuizId(@PathVariable ("id") Integer id)
	{
		return quizService.getQuizQuestions(id);
	}
	

	//---- GET/READ Operations  END --------------------------------------------------------------------------//
	
	//---- POST/CREATE Operations  BEGIN ---------------------------------------------------------------------//
	
	
	
	/* 
	 * Creates Quiz.
	 * -------------
	 * For accepting requests in the path-> 
	 * localhost:8080/quiz/create?category=<category>&numQ=<numQ>&title=<title>
	 * 
	 * This method allows users to create a quiz.
	 * We'll take three parameters as input from the request URL: 
	 * 		1. String category: Category of questions.
	 * 		2. int numQ :       Number of questions in the quiz
	 *  	3. String title:    The name/title of the quiz created 
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
	
	/* Accepts responses for the quiz.
	 * -------------------------------
	 * For accepting requests in the path-> localhost:8080/quiz/submit/<preferred_id>
	 * Here, in the URL we have to mention the Quiz ID.
	 * The response for the Quiz has to be done in JSON format. 
	 * While submitting the response, we are sending two attributes: Question ID and the answer.
	 * For accepting this response, we are creating a new Model class: Response.java in the model package
	 * and has two attributes: Question ID and answer. It isn't related to ResponseEntity in any way.
	 * This function accepts a List of Response objects. 
	 * Returns the score by calculating the correct answers mentioned.
	 */
	@PostMapping("submit/{id}")
	public ResponseEntity<String> submitQuiz(@PathVariable("id")Integer id ,
			@RequestBody List<Response> responses)
	{
		return quizService.calculateScore(id, responses);
	}
	
	
	//---- POST/CREATE Operations END ------------------------------------------------------------------//
	
	//---- DELETE Operations BEGIN ---------------------------------------------------------------------//
	
	
	/* Deletes a Quiz.
	 * ---------------
	 * For accepting requests in the path-> localhost:8080/quiz/delete/<preferred_id>
	 * Here, we are accepting the QuizID associated with the QuizTitle in the DB from the user.
	 * The quiz gets deleted.
	 */
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteQuiz(@PathVariable("id")Integer id)
	{
		return quizService.deleteQuizById(id);
	}
}