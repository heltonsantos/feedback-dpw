package com.br.datafeed.service.impl;

import javax.inject.Inject;

import com.br.datafeed.model.Feedback;
import com.br.datafeed.repository.IFeedbackRepository;
import com.br.datafeed.service.IFeedbackService;

public class FeedbackService implements IFeedbackService{

	@Inject
	IFeedbackRepository repository;

	public void adicionarFeedback(Feedback feedback) {
		repository.adicionarFeedback(feedback);	
	}

	public void atualizarFeedback(Feedback feedback) {
		repository.atualizarFeedback(feedback);
		
	}

	public Feedback buscarFeedback(int dataset_id) {
		return repository.buscarFeedback(dataset_id);
	}
	
	public Feedback buscarFeedbackView(int dataset_id) {
		return repository.buscarFeedbackView(dataset_id);
	}
}
