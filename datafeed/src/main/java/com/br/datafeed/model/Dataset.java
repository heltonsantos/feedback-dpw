package com.br.datafeed.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="dataset")
public class Dataset implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="identifier", unique=true, nullable=false)
	private String identifier;
	
	@Column(name="hasRating", nullable=false)
	private double hasRating;
	
	@OneToMany(mappedBy="hasTarget", fetch = FetchType.EAGER)
	private List<Feedback> feedback;
	
	public Dataset() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public double getHasRating() {
		return hasRating;
	}
	public void setHasRating(double hasRating) {
		this.hasRating = hasRating;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

}
