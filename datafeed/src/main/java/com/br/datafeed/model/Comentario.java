package com.br.datafeed.model;

import java.util.HashSet;
import java.util.Set;

public class Comentario {
	
	private Integer id;
	private String comentario;
	private Double avaliacao;
	private Set<Resposta> resposta = new HashSet<Resposta>();
	private Feedback feedback;
	
	public Comentario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Set<Resposta> getResposta() {
		return resposta;
	}

	public void setResposta(Set<Resposta> resposta) {
		this.resposta = resposta;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

		
}
