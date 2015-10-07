package com.br.datafeed.repository.dao;

import com.br.datafeed.model.FeedbackTest;
import com.br.datafeed.repository.IFeedbackTestRepository;
import com.google.inject.Singleton;

@Singleton
public class FeedbackTestRepository implements IFeedbackTestRepository{

	public String testInject(){
		return "Hello World!";
	}
	
	public FeedbackTest getFeedbackTest(){
		FeedbackTest feed = new FeedbackTest();
		feed.setId(1);
		feed.setComentario("Testando o feedback!");
		
		return feed;
	}
	
}
