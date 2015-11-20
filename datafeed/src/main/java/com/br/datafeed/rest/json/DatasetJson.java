package com.br.datafeed.rest.json;

import java.util.List;

public class DatasetJson {

	private String identifier;
	private String title;
	private double hasRating;
	private List<FeedbackJson> feedback;
	
	public DatasetJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getHasRating() {
		return hasRating;
	}

	public void setHasRating(double hasRating) {
		this.hasRating = hasRating;
	}

	public List<FeedbackJson> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<FeedbackJson> feedback) {
		this.feedback = feedback;
	}
	
}
