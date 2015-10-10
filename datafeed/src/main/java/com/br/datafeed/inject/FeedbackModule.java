package com.br.datafeed.inject;

import com.br.datafeed.repository.IFeedbackRepository;
import com.br.datafeed.repository.dao.FeedbackRepository;
import com.br.datafeed.service.IFeedbackService;
import com.br.datafeed.service.impl.FeedbackService;
import com.google.inject.AbstractModule;

public class FeedbackModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IFeedbackService.class).to(FeedbackService.class);
		bind(IFeedbackRepository.class).to(FeedbackRepository.class);
		
	}

}
