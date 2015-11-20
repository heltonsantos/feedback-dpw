package com.br.datafeed.service;

import com.br.datafeed.model.Dataset;
import com.br.datafeed.rest.json.DatasetJson;

public interface IDatasetService {
	
	public void adicionarDataset(Dataset dataset);
	public void atualizarDataset(Dataset dataset);
	public Dataset buscarDataset(String identifier);
	public Dataset buscarDatasetView(String identifier);
	public DatasetJson buscarDatasetJson(String identifier);
}
