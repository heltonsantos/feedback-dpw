package com.br.datafeed.service.impl;

import javax.inject.Inject;

import com.br.datafeed.model.Dataset;
import com.br.datafeed.repository.IDatasetRepository;
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
}
