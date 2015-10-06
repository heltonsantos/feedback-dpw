package com.br.datafeed.inject;

import com.br.datafeed.repository.IFeedbackTestRepository;
import com.br.datafeed.repository.dao.FeedbackTestRepository;
import com.br.datafeed.service.IFeedbackTestService;
import com.br.datafeed.service.impl.FeedbackTestService;
import com.google.inject.AbstractModule;

public class FeedbackTestModule extends AbstractModule{
	
	@Override
	public void configure()
	{
		bind(IFeedbackTestService.class).to(FeedbackTestService.class);
		bind(IFeedbackTestRepository.class).to(FeedbackTestRepository.class);
	}

}
