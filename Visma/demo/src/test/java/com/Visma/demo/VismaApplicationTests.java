package com.Visma.demo;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
class VismaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
			throws IOException {

		// Given
		String name = "notExists";
		HttpUriRequest request = new HttpGet( "https://localhost:8080/get-book" + name );

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

		// Then
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 404);
	}

}
