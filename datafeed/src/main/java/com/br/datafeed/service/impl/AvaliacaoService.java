package com.br.datafeed.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.br.datafeed.model.Avaliacao;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.repository.IAvaliacaoRepository;
import com.br.datafeed.repository.IFeedbackRepository;
import com.br.datafeed.service.IAvaliacaoService;

public class AvaliacaoService implements IAvaliacaoService{
	
	@Inject
	IAvaliacaoRepository avaliacaoRepository;
	
	@Inject
	IFeedbackRepository feedbackRepository;

	public void adicionarAvaliacao(int dataset_id, Avaliacao avaliacao) {
		
		Feedback feedback = feedbackRepository.buscarFeedback(dataset_id);
		
		if(feedback.getAvaliacao_media() == 0){
			feedback.setAvaliacao_media(avaliacao.getAvaliacao());
		}
		else{
			double avaliacao_final = (feedback.getAvaliacao_media() + avaliacao.getAvaliacao())/2;
			
			feedback.setAvaliacao_media(avaliacao_final);
		}
		
		feedbackRepository.atualizarFeedback(feedback);
		
		avaliacao.setFeedback(feedback);
		avaliacaoRepository.adicionarAvaliacao(avaliacao);
		
	}

	public void atualizarAvaliacao(int dataset_id, Avaliacao avaliacao) {
		
		Feedback feedback = feedbackRepository.buscarFeedback(dataset_id);
		
		if(feedback.getAvaliacao_media() == 0){
			feedback.setAvaliacao_media(avaliacao.getAvaliacao());
		}
		else{
			double avaliacao_final = (feedback.getAvaliacao_media() + avaliacao.getAvaliacao())/2;
			
			feedback.setAvaliacao_media(avaliacao_final);
		}
		
		feedbackRepository.atualizarFeedback(feedback);
		
		avaliacao.setFeedback(feedback);
		avaliacaoRepository.atualizarAvaliacao(avaliacao);
		
	}

	public Avaliacao buscarAvaliacao(int avaliacao_id) {
		return avaliacaoRepository.buscarAvaliacao(avaliacao_id);
	}

	public void deletarAvaliacao(int avaliacao_id) {
		Avaliacao avaliacao = avaliacaoRepository.buscarAvaliacao(avaliacao_id);
		avaliacaoRepository.deletarAvaliacao(avaliacao);
		
	}

	public List<Avaliacao> buscarAvaliacaoList(int dataset_id, int offset, int limit) {
		return avaliacaoRepository.buscarAvaliacaoList(dataset_id, offset, limit);
	}

}
