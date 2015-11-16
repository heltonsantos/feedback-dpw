package com.br.datafeed.rest.json;

import java.util.Date;

public class FeedbackAnnotatedView {
	
	private int feedbackId;
	private Date dateSubmitted;
	private String hasBody;
	private String motivatedBy;
	private int personId;
	private String giveName;
	private String mbox;
	
	public FeedbackAnnotatedView() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public Date getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public String getHasBody() {
		return hasBody;
	}
	public void setHasBody(String hasBody) {
		this.hasBody = hasBody;
	}
	public String getMotivatedBy() {
		return motivatedBy;
	}
	public void setMotivatedBy(String motivatedBy) {
		this.motivatedBy = motivatedBy;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getGiveName() {
		return giveName;
	}
	public void setGiveName(String giveName) {
		this.giveName = giveName;
	}
	public String getMbox() {
		return mbox;
	}
	public void setMbox(String mbox) {
		this.mbox = mbox;
	}
	
	
}
