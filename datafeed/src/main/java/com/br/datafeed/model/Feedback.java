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
@Table(name="feedback")
public class Feedback {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="DATASET_ID", unique=true, nullable=false)
	private String dataset_id;
	
	@Column(name="AVALIACAO_MEDIA", nullable=false)
	private double avaliacao_media;
	
	@OneToMany(mappedBy="feedback", fetch = FetchType.EAGER)
	private List<Avaliacao> avaliacao;
	
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
	public String getDataset_id() {
		return dataset_id;
	}
	public void setDataset_id(String dataset_id) {
		this.dataset_id = dataset_id;
	}
	public double getAvaliacao_media() {
		return avaliacao_media;
	}
	public void setAvaliacao_media(double avaliacao_media) {
		this.avaliacao_media = avaliacao_media;
	}

	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(List<Avaliacao> avaliacao) {
		this.avaliacao = avaliacao;
	}

}
