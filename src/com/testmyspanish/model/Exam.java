package com.testmyspanish.model;

import java.io.Serializable;
import java.util.List;

import com.testmyspanish.persistence.dao.QuestionDAO;

public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Question> questions;
	
	public Exam(Integer numberOfQuestions) {
		questions = fillQuestions(numberOfQuestions);
	}

	public Question consumeQuestion() {
		if (questions.isEmpty()) {
			return null;
		}
		return questions.remove(0);
	}

	private List<Question> fillQuestions(Integer numberOfQuestions) {
		return QuestionDAO.readRandomQuestions(numberOfQuestions);
	}
}
