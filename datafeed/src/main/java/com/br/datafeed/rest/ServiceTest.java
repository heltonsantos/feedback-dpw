package com.br.datafeed.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.br.datafeed.model.FeedbackTest;

@Path("/test")
public class ServiceTest {
	
	@GET
    @Path("/get/json/")
    @Produces("application/json")
    public FeedbackTest getFeedback(){
		
		FeedbackTest feed = new FeedbackTest();
		feed.setId(1);
		feed.setComentario("up");
		
		return feed;		
    }
}
