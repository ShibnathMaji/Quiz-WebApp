package com.example.quizwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizwebapp.dao.QuestionDAO;
import com.example.quizwebapp.dao.QuizDAO;
import com.example.quizwebapp.model.Question;
import com.example.quizwebapp.model.Quiz;

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

}
