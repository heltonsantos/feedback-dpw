package com.br.datafeed.rest.json;

import com.br.datafeed.model.Feedback;
import com.br.datafeed.model.Person;

public class FeedbackPerson {
	
	private Feedback feedback;
	private Person person;
	
	public FeedbackPerson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
