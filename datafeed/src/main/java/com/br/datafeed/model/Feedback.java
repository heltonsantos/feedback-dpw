package com.br.datafeed.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Feedback {
	
	private Integer id;
	private Integer dataset_id;
	private double avaliacao_media;
	private List<Avaliacao> avaliacao;
	
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDataset_id() {
		return dataset_id;
	}
	public void setDataset_id(Integer dataset_id) {
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
