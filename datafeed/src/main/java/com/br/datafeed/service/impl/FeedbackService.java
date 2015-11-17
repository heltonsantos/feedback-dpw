package com.br.datafeed.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.br.datafeed.model.Feedback;
import com.br.datafeed.model.Dataset;
import com.br.datafeed.repository.IFeedbackRepository;
import com.br.datafeed.repository.IDatasetRepository;
import com.br.datafeed.service.IFeedbackService;

public class FeedbackService implements IFeedbackService{
	
	@Inject
	IFeedbackRepository feedbackRepository;
	
	@Inject
	IDatasetRepository datasetRepository;

	public void adicionarFeedback(String identifier, Feedback feedback) {
		
		Dataset dataset = datasetRepository.buscarDataset(identifier);
		
		if(dataset.getHasRating() == 0 && feedback.getMotivatedBy().equals(Feedback.MOTIVATED_BY_RATING)){
			dataset.setHasRating(Double.parseDouble(feedback.getHasBody()));
			
			datasetRepository.atualizarDataset(dataset);
			
		}
		else if(dataset.getHasRating() != 0 && feedback.getMotivatedBy().equals(Feedback.MOTIVATED_BY_RATING)){
			double rating = (dataset.getHasRating() + Double.parseDouble(feedback.getHasBody()))/2;
			
			dataset.setHasRating(rating);
			datasetRepository.atualizarDataset(dataset);
			
		}
		
		feedback.setHasTarget(dataset);
		feedbackRepository.adicionarFeedback(feedback);
		
	}

	public void atualizarFeedback(String identifier, Feedback feedback) {
		
		Dataset dataset = datasetRepository.buscarDataset(identifier);
		
		if(dataset.getHasRating() == 0 && feedback.getMotivatedBy().equals(Feedback.MOTIVATED_BY_RATING)){
			dataset.setHasRating(Double.parseDouble(feedback.getHasBody()));
			
			datasetRepository.atualizarDataset(dataset);
		}
		else if (dataset.getHasRating() != 0 && feedback.getMotivatedBy().equals(Feedback.MOTIVATED_BY_RATING)){
			double rating = (dataset.getHasRating() + Double.parseDouble(feedback.getHasBody()))/2;
			
			dataset.setHasRating(rating);
			datasetRepository.atualizarDataset(dataset);
			
		}
		
		feedback.setHasTarget(dataset);
		feedbackRepository.atualizarFeedback(feedback);
		
	}

	public Feedback buscarFeedback(int feedback_id) {
		return feedbackRepository.buscarFeedback(feedback_id);
	}

	public void deletarFeedback(int feedback_id) {
		Feedback feedback = feedbackRepository.buscarFeedback(feedback_id);
		feedbackRepository.deletarFeedback(feedback);
		
	}

	public List<Feedback> buscarFeedbackList(String dataset_id, int offset, int limit) {
		return feedbackRepository.buscarFeedbackList(dataset_id, offset, limit);
	}

}
