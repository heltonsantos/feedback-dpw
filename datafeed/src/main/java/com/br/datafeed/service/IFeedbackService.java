package com.br.datafeed.service;

import java.util.List;

import com.br.datafeed.model.Feedback;
import com.br.datafeed.rest.json.FeedbackAnnotatedView;

public interface IFeedbackService {
	
	public void adicionarFeedback(String identifier, Feedback feedback);
	public void atualizarFeedback(String identifier, Feedback feedback);
	public Feedback buscarFeedback(int feedback_id);
	public void deletarFeedback(int feedback_id);
	public List<Feedback> buscarFeedbackList(String identifier, int offset, int limit);
	public List<FeedbackAnnotatedView> buscarFeedbackListAnnotated(String identifier, int offset, int limit);
}
