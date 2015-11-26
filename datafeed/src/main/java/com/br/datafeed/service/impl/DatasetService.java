package com.br.datafeed.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.datafeed.model.Dataset;
import com.br.datafeed.model.Feedback;
import com.br.datafeed.repository.IDatasetRepository;
import com.br.datafeed.rest.json.DatasetJson;
import com.br.datafeed.rest.json.FeedbackJson;
import com.br.datafeed.rest.json.PersonJson;
import com.br.datafeed.service.IDatasetService;

public class DatasetService implements IDatasetService{

	@Inject
	IDatasetRepository repository;

	public void adicionarDataset(Dataset dataset) {
		repository.adicionarDataset(dataset);	
	}

	public void atualizarDataset(Dataset dataset) {
		repository.atualizarDataset(dataset);
		
	}

	public Dataset buscarDataset(String identifier) {
		return repository.buscarDataset(identifier);
	}
	
	public Dataset buscarDatasetView(String identifier) {
		return repository.buscarDatasetView(identifier);
	}

	public DatasetJson buscarDatasetJson(String identifier) {
		Dataset dataset = new Dataset();
		DatasetJson datasetJson = new DatasetJson();
		
		dataset = repository.buscarDataset(identifier);
		
		if(dataset != null){
			//Montar DatasetJson
			datasetJson.setIdentifier(dataset.getIdentifier());
			datasetJson.setHasRating(dataset.getHasRating());
			
			if(dataset.getFeedback() != null){
				List<FeedbackJson> listFeedbackJson = new ArrayList<FeedbackJson>();
				
				for(Feedback feedback:dataset.getFeedback()){
					FeedbackJson feedbackJson = new FeedbackJson();
					
					if(feedback.getAnnotatedBy() != null){
						PersonJson personJson = new PersonJson();
						personJson.setGiveName(feedback.getAnnotatedBy().getGiveName());
						personJson.setMbox(feedback.getAnnotatedBy().getMbox());
						feedbackJson.setAnnotatedBy(personJson);
					}
					
					feedbackJson.setDateSubmitted(feedback.getDateSubmitted());
					feedbackJson.setHasBody(feedback.getHasBody());
					feedbackJson.setMotivatedBy(feedback.getMotivatedBy());
					feedbackJson.setHasTarget(dataset.getIdentifier());
					
					
					listFeedbackJson.add(feedbackJson);
				}
				datasetJson.setFeedback(listFeedbackJson);
			}
		}
		
		return datasetJson;
	}
}
