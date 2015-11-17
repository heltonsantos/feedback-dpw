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
@Table(name="person")
public class Person {
	
	@Id
    @GeneratedValue
    @Column(name="id")
	private int id;
	
	@Column(name="giveName", nullable=false)
	private String giveName;
	
	@Column(name="mbox", unique=true)
	private String mbox;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
