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

@Entity
@Table(name="avaliacao")
public class Avaliacao {
	
	@Id
    @GeneratedValue
    @Column(name="ID")
	private int id;
	
	@Column(name="DATA_AVALIACAO", nullable=false)
	private Date data_avaliacao;
	
	@Column(name="NOME_USUARIO", nullable=false)
	private String nome_usuario;
	
	@Column(name="EMAIL_USUARIO", nullable=false)
	private String email_usuario;
	
	@Column(name="AVALIACAO", nullable=false)
	private double avaliacao;
	
	@Column(name="COMENTARIO")
	private String comentario;
	
	@ManyToOne
	@JoinColumn(name="FEEDBACK_ID", nullable=false)
	@JsonIgnore
	private Feedback feedback;
	
	public Avaliacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData_avaliacao() {
		return data_avaliacao;
	}

	public void setData_avaliacao(Date data_avaliacao) {
		this.data_avaliacao = data_avaliacao;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getEmail_usuario() {
		return email_usuario;
	}

	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
		
}
