package com.testmyspanish.model;

import java.io.Serializable;
import java.util.List;

import com.testmyspanish.persistence.dao.QuestionDAO;

public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer actualScore = 0;
	private Integer totalScore;
	private List<Question> questions;
	
	public Exam(Integer numberOfQuestions) {
		totalScore = numberOfQuestions;
		questions = fillQuestions(numberOfQuestions);
	}

	public Question consumeQuestion() {
		if (questions.isEmpty()) {
			return null;
		}
		return questions.remove(0);
	}

	public Integer getActualScore() {
		return actualScore;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setActualScore(Integer actualScore) {
		this.actualScore = actualScore;
	}

	private List<Question> fillQuestions(Integer numberOfQuestions) {
		return QuestionDAO.readRandomQuestions(numberOfQuestions);
	}
}
