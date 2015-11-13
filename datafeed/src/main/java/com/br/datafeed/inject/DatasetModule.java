package com.br.datafeed.inject;

import com.br.datafeed.repository.IDatasetRepository;
import com.br.datafeed.repository.dao.DatasetRepository;
import com.br.datafeed.service.IDatasetService;
import com.br.datafeed.service.impl.DatasetService;
import com.google.inject.AbstractModule;

public class DatasetModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IDatasetService.class).to(DatasetService.class);
		bind(IDatasetRepository.class).to(DatasetRepository.class);
		
	}

}
