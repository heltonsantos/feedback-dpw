package com.br.datafeed.repository.dao;

import com.br.datafeed.repository.IFeedbackTestRepository;
import com.google.inject.Singleton;

@Singleton
public class FeedbackTestRepository implements IFeedbackTestRepository{

	public String testInject()
	{
		return "Hello World!";
	}
	
}
