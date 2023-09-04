package com.example.quizwebapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizwebapp.dao.QuestionDAO;
import com.example.quizwebapp.dao.QuizDAO;
import com.example.quizwebapp.model.Question;
import com.example.quizwebapp.model.QuestionWrapper;
import com.example.quizwebapp.model.Quiz;
import com.example.quizwebapp.model.Response;

@Service
public class QuizService 
{
	@Autowired
	QuizDAO quizDAO;
	@Autowired
	QuestionDAO questionDAO;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) 
	{
		/* Whenever createQuiz() runs, we want to create a quiz.
		 * Number of Questions and Category of questions are given by user.
		 * We want the quiz questions to be picked in a randomized order.
		 * If it is not randomized but serialized, we will always get the same questions for every quiz.
		 */ 
		List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		//Saving the quiz generated in a table.
		quizDAO.save(quiz);
		
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	//----------------------------------------------------------------------------	
	
	/* It shows the List of all Quizzes present in the DB, the ids associated with it and the questions
	 * present in the quiz.
	*/
	public ResponseEntity<List<Quiz>> getAllQuizzes() 
	{
		try
		{
			return new ResponseEntity<List<Quiz>>(quizDAO.findAll(), HttpStatus.OK);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ResponseEntity<List<Quiz>>(quizDAO.findAll(), HttpStatus.BAD_REQUEST);
	}
	
	//----------------------------------------------------------------------------	
	
	
	/* This method takes the QuizID as input, finds the quiz instance associated with the ID
	 * and returns the questions in the Quiz.
	 * Each of the Questions in the quiz are of the type -> Question.java [Entity/Model class]
	 * This function converts that into -> QuestionWrapper.java [Entity/Model class]
	 * Reason for this conversion is:
	 * We don't need to send all the attributes mentioned in the Question.java to the user
	 * We only need to send Question ID, Question Title and the Options 1-4.
	 * 
	 * QuestionWrapper.java has only the above mentioned attributes and we send the values
	 * to a parameterized constructor.
	 */
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) 
	{
		/* Optional return type signifies that we might not get a response in case the id is not present.
		 * Here, we are storing a quiz instance associated with the id. .
		 */
		Optional<Quiz> quiz = quizDAO.findById(id);
		
		/* questionFromDB extract the List of Questions present in the quiz instance. 
		 * get() is used because quiz's return type is Optional
		 */ 
		List<Question> questionFromDB = quiz.get().getQuestions();
		
		// Defining questionForUser as an ArrayList. It'll contain objects of type QuestionWrapper.
		List<QuestionWrapper>questionForUser = new ArrayList<QuestionWrapper>();
		
		// Converting Question object to QuestionWrapper object
		for(Question q: questionFromDB)
		{
			//Generating a new QuestionWrapper instance
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), 
					q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			
			//Adding this instance into the ArrayList questionForUser
			questionForUser.add(qw);
		}
		return new ResponseEntity<>(questionForUser, HttpStatus.OK);
	}
	
	//----------------------------------------------------------------------------	
	
	/* This method takes Quiz id and the List of Responses that the user sends to the console.
	 * Each of these "Responses" are an object of Response.java model class.
	 * We are extracting the attribute "answer" from each of these Response objects.
	 * Then, we are extracting the attribute "rightAnswer" from each of then Question objects present in the Quiz.
	 * If these two are the same, that means the answer sent by the user was correct.
	 */

	public ResponseEntity<String> calculateScore(Integer id, List<Response> responses) 
	{
		Optional<Quiz> quiz = quizDAO.findById(id);
		
		/* questionFromDB extract the List of Questions present in the quiz instance. 
		 * get() is used because quiz's return type is Optional
		 */
		List<Question> questions = quiz.get().getQuestions();
		
		int rightAnswerCounter = 0, questionCounter = 0;
		
		for(Response response : responses)
		{
			String answer = response.getAnswer();	
			
			/* int questionCounter acts as an iterator.
			 * questions is a List of Question objects. 
			 * We access the list values by using get(id_of_the_value we want).
			 * questions.get(questionCounter) gets the Question object
			 * getRightAnswer() gets the "rightAnswer" attribute of the Question object.
			 */  
			String correctAnswer = questions.get(questionCounter).getRightAnswer();
			
			if(answer.equals(correctAnswer))
				rightAnswerCounter++;
			
			// Since we have to iterate through the List of Questions, we increment the iterator. 
			questionCounter++;
		}
		String sendResultToUser = "Number of correct answers = "+rightAnswerCounter+" out of " + questionCounter;
		return new ResponseEntity<String>(sendResultToUser, HttpStatus.OK);
	}
	
	//----------------------------------------------------------------------------	
	
	/* This method takes Quiz id and checks the DB if there's any Quiz associated with that Id.
	 * If found, it deletes the quiz object.
	 */


	public ResponseEntity<String> deleteQuizById(Integer id) 
	{
		Optional<Quiz> optionalObject = quizDAO.findById(id);
		try
		{
			Quiz quizObject = optionalObject.get();
			quizDAO.delete(quizObject);
			
			return new ResponseEntity<>("Deletion successful", HttpStatus.NO_CONTENT);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
	}

}