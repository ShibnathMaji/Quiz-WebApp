package com.example.quizwebapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
// This is our Entity / Model class and contains all the data needed for the application
@Entity
@Table(name = "QuestionCopyTable")
public class Question 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;  // We use Integer instead of int because JPA Repository requires wrapper class.
	private String category;
	private String difficultyLevel;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String rightAnswer;
}