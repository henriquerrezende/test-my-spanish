package com.testmyspanish.model;

import java.util.List;

public class Exam {
	
	private Integer id;
	private Integer numberOfQuestions;
	private List<Question> questions;
	
	public Exam(Integer numberOfQuestions) {
		questions = fillQuestions(numberOfQuestions);
	}

	private List<Question> fillQuestions(Integer numberOfQuestions) {
		//TODO: Implement this
		throw new UnsupportedOperationException();
	}

	public Integer getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public Integer getId() {
		return id;
	}

}
