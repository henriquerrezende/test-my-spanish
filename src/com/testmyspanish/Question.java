package com.testmyspanish;

import java.util.List;

public class Question {
	
	private String title;
	private Integer correctAnswerId;
	private List<Answer> answers;
	
	public Question(String title, Integer answerId, List<Answer> answers) {
		this.title = title;
		this.correctAnswerId = answerId;
		this.answers = answers;
	}

	public String getTitle() {
		return title;
	}

}
