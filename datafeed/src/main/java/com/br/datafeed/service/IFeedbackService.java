package com.br.datafeed.service;

import com.br.datafeed.model.Feedback;

public interface IFeedbackService {
	
	public void adicionarFeedback(Feedback feedback);
	public void atualizarFeedback(Feedback feedback);
	public Feedback buscarFeedback(int dataset_id);
}
