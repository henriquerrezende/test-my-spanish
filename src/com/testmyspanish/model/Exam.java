package com.testmyspanish.model;

import java.util.List;

import com.testmyspanish.persistence.dao.QuestionDAO;

public class Exam {
	
	private Integer numberOfQuestions;
	private List<Question> questions;
	
	public Exam(Integer numberOfQuestions) {
		questions = fillQuestions(numberOfQuestions);
	}

	public Integer getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	private List<Question> fillQuestions(Integer numberOfQuestions) {
		return QuestionDAO.readRandomQuestions(numberOfQuestions);
	}
}
