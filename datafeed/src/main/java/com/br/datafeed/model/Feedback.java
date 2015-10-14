package com.br.datafeed.model;

import java.util.List;

public class Feedback {
	
	private int id;
	private int dataset_id;
	private double avaliacao_media;
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
	public int getDataset_id() {
		return dataset_id;
	}
	public void setDataset_id(int dataset_id) {
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
