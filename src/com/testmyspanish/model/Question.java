package com.testmyspanish.model;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String question;
	private Long correctAnswerId;
	private List<Answer> answers;
	
	public Question(String question, Long answerId, List<Answer> answers) {
		this.question = question;
		this.correctAnswerId = answerId;
		this.answers = answers;
	}

	public String getQuestion() {
		return question;
	}
	
	public Long getCorrectAnswerId() {
		return correctAnswerId;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}

}
