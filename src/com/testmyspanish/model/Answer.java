package com.testmyspanish.model;

public class Answer {
	
	private Integer id;
	private String answer;
	
	public Answer(Integer id, String answer) {
		this.id = id;
		this.answer = answer;
	}

	public Integer getId() {
		return id;
	}
	public String getAnswer() {
		return answer;
	}
	
}
