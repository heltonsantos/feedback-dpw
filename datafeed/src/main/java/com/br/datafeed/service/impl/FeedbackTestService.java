package com.br.datafeed.service.impl;

import javax.inject.Inject;

import com.br.datafeed.model.FeedbackTest;
import com.br.datafeed.repository.IFeedbackTestRepository;
import com.br.datafeed.service.IFeedbackTestService;

public class FeedbackTestService implements IFeedbackTestService{
	
	@Inject
	IFeedbackTestRepository repository;
	
	
	
	public String testInject(){
		return this.repository.testInject();
	}
	
	public FeedbackTest getFeedbackTest(){
		return this.repository.getFeedbackTest();
	}

}
