package com.br.datafeed.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FeedbackTest {
	
	int id;
	String comentario;
	
	public FeedbackTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
}
