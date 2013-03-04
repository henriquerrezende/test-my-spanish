package com.testmyspanish.model;

import java.io.Serializable;

public class Answer implements Serializable {
	
	private static final long serialVersionUID = 1L;

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
