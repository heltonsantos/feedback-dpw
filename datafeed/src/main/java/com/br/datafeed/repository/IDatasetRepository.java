package com.br.datafeed.repository;

import com.br.datafeed.model.Dataset;

public interface IDatasetRepository {

	public void adicionarDataset(Dataset dataset);
	public void atualizarDataset(Dataset dataset);
	public Dataset buscarDataset(String identifier);
	public Dataset buscarDatasetView(String identifier);
}
