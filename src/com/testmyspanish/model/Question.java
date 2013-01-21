package com.testmyspanish.model;

import java.util.List;


public class Question {
	
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
