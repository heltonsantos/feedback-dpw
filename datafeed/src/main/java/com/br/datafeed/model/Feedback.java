package com.br.datafeed.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@Table(name="feedback")
public class Feedback implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	public final static String MOTIVATED_BY_RATING = "RATING";
	public final static String MOTIVATED_BY_CORRECTION = "CORRECTION";
	
	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@JsonSerialize(using=com.br.datafeed.util.JsonDateSerializer.class)
	@Column(name="dateSubmitted", nullable=false)
	private Date dateSubmitted;
	
	@Column(name="hasBody", nullable=false)
	private String hasBody;
	
	@Column(name="motivatedBy", nullable=false)
	private String motivatedBy;
	
	@ManyToOne
	@JoinColumn(name="hasTarget", referencedColumnName="identifier", nullable=false)
	@JsonIgnore
	private Dataset hasTarget;
	
	@ManyToOne
	@JoinColumn(name = "annotatedBy")
	private Person annotatedBy;
	
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Dataset getHasTarget() {
		return hasTarget;
	}

	public void setHasTarget(Dataset hasTarget) {
		this.hasTarget = hasTarget;
	}

	public Person getAnnotatedBy() {
		return annotatedBy;
	}

	public void setAnnotatedBy(Person annotatedBy) {
		this.annotatedBy = annotatedBy;
	}
		
}
