package com.br.datafeed.repository;

import java.util.List;

import com.br.datafeed.model.Feedback;

public interface IFeedbackRepository {
	
	public void adicionarFeedback(Feedback feedback);
	public void atualizarFeedback(Feedback feedback);
	public Feedback buscarFeedback(int feedback_id);
	public void deletarFeedback(Feedback feedback);
	public List<Feedback> buscarFeedbackList(String identifier, int offset, int limit);

}
