package com.br.datafeed.tdd;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.br.datafeed.model.Feedback;

public class ServicesRestTest {

	
	public void executarPost(String json, String url) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url);

			StringEntity input = new StringEntity(json);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		  } catch (MalformedURLException e) {

			e.printStackTrace();
		
		  } catch (IOException e) {

			e.printStackTrace();

		  }
	}
	
	@Test
	public void adicionarFeedback() throws JsonGenerationException, JsonMappingException, IOException {
		String url = "http://localhost:8080/datafeed/rest/feedback/adicionar";
		Feedback feedback = new Feedback();		
		feedback.setDataset_id(7);
		feedback.setAvaliacao_media(7);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(feedback);
		System.out.println(json);
		
		executarPost(json, url);
		
	}

}
