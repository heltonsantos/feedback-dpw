package com.br.datafeed.service;

import com.br.datafeed.model.Dataset;

public interface IDatasetService {
	
	public void adicionarDataset(Dataset dataset);
	public void atualizarDataset(Dataset dataset);
	public Dataset buscarDataset(String identifier);
	public Dataset buscarDatasetView(String identifier);
}
