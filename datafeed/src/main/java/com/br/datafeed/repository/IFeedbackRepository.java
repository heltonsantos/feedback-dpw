package com.br.datafeed.repository;

import com.br.datafeed.model.Feedback;

public interface IFeedbackRepository {

	public void adicionarFeedback(Feedback feedback);
	public Feedback pegarFeedback(int dataset_id);
}
