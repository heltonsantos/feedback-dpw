package com.br.datafeed.rest.json;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class FeedbackJson {
	
	@JsonSerialize(using=com.br.datafeed.util.JsonDateSerializer.class)
	private Date dateSubmitted;
	private String hasBody;
	private String motivatedBy;
	private String hasTarget;
	private PersonJson annotatedBy;
	
	public FeedbackJson() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getHasTarget() {
		return hasTarget;
	}

	public void setHasTarget(String hasTarget) {
		this.hasTarget = hasTarget;
	}

	public PersonJson getAnnotatedBy() {
		return annotatedBy;
	}

	public void setAnnotatedBy(PersonJson annotatedBy) {
		this.annotatedBy = annotatedBy;
	}

}
