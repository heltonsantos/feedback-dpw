package com.br.datafeed.repository;

import com.br.datafeed.model.Avaliacao;

public interface IAvaliacaoRepository {
	
	public void adicionarAvaliacao(Avaliacao avaliacao);
	public void atualizarAvaliacao(Avaliacao avaliacao);
	public Avaliacao buscarAvaliacao(int avaliacao_id);
	public void deletarAvaliacao(Avaliacao avaliacao);

}
