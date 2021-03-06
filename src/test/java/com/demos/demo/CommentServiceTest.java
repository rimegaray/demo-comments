package com.demos.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.demos.demo.comments.business.impl.CommentServiceImpl;
import com.demos.demo.comments.dao.impl.CommentDaoImpl;
import com.demos.demo.comments.model.Comment;
import com.demos.demo.comments.model.Data;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

	@InjectMocks
	CommentServiceImpl service;
	
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	@Mock
	CommentDaoImpl daoMock;
	
	@Test
	public void shouldReturnOk() throws JsonParseException, JsonMappingException, IOException {
		Data data = JSON_MAPPER.readValue(new File("src/main/resources/mijson.json"), Data.class);
		
		ArrayList<Comment> myComments = JSON_MAPPER.readValue(new File("src/main/resources/webservice.json"), 
				JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Comment.class));
		
		Mockito.when(daoMock.searchComments()).thenReturn(Flux.fromIterable(myComments));
		
		assertEquals(data.getData().get(0), service.searchComments().block().getData().get(0));
		assertEquals(data.getData().get(1), service.searchComments().block().getData().get(1));
		assertEquals(data.getData().get(2), service.searchComments().block().getData().get(2));
		
		StepVerifier.create(service.searchComments())
	    .expectNextCount(1)
	    .expectComplete()
	    .verify();
	}
	
}
