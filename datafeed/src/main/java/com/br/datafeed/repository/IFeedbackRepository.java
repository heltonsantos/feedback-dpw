package com.br.datafeed.repository;

import com.br.datafeed.model.Feedback;

public interface IFeedbackRepository {

	public void adicionarFeedback(Feedback feedback);
	public void atualizarFeedback(Feedback feedback);
	public Feedback buscarFeedback(String dataset_id);
	public Feedback buscarFeedbackView(String dataset_id);
}
