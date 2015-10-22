package com.br.datafeed.service;

import java.util.List;

import com.br.datafeed.model.Avaliacao;

public interface IAvaliacaoService {
	
	public void adicionarAvaliacao(String dataset_id, Avaliacao avaliacao);
	public void atualizarAvaliacao(String dataset_id, Avaliacao avaliacao);
	public Avaliacao buscarAvaliacao(int avaliacao_id);
	public void deletarAvaliacao(int avaliacao_id);
	public List<Avaliacao> buscarAvaliacaoList(String dataset_id, int offset, int limit);
	
}
