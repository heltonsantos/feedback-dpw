package com.br.datafeed.service;

import java.util.List;

import com.br.datafeed.model.Feedback;

public interface IFeedbackService {
	
	public void adicionarFeedback(String identifier, Feedback feedback);
	public void atualizarFeedback(String identifier, Feedback feedback);
	public Feedback buscarFeedback(int feedback_id);
	public void deletarFeedback(int feedback_id);
	public List<Feedback> buscarFeedbackList(String identifier, int offset, int limit);
	
}
