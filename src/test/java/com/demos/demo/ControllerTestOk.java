package com.demos.demo;

import static org.junit.Assert.assertNotNull;

import com.demos.demo.expose.web.CommentController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
    properties = "config.base.url = https://jsonplaceholder.typicode.com/comments")
public class ControllerTestOk {

	@Autowired
	CommentController controller;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	public void shouldReturnIs4xxClientErrorWhenResponseIsNotOk() {
		assertNotNull(webTestClient.get()
				  .uri("https://jsonplaceholder.typicode.com/commentss")
				  .accept(MediaType.TEXT_PLAIN).exchange()
				  .expectStatus().is4xxClientError());
	}
	
	@Test
	public void shouldReturnIsOkWhenResponseIsOk() {
		assertNotNull(webTestClient.get()
				  .uri("https://jsonplaceholder.typicode.com/comments")
				  .accept(MediaType.TEXT_PLAIN).exchange()
				  .expectStatus().isOk());
	}
	
	@Test
	public void dataResponseShouldReturnNotNullWhenResponseIsOk() {
		assertNotNull(controller.searchComments().block().getData().get(0));
	}
}
