package com.br.datafeed.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

import com.br.datafeed.inject.FeedbackTestModule;
import com.br.datafeed.service.IFeedbackTestService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectTest {

	@Test
	public void test() {
		Injector injector = Guice.createInjector(new FeedbackTestModule());
		IFeedbackTestService servico = injector.getInstance(IFeedbackTestService.class);
		
		System.out.println(servico.testInject());
	}

}
