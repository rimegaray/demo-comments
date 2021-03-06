package com.demos.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.demos.demo.comments.dao.impl.CommentDaoImpl;
import com.demos.demo.comments.model.Comment;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@RunWith(MockitoJUnitRunner.class)
public class CommentDaoTest {

	@InjectMocks
	CommentDaoImpl dao;
	
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	@Mock
	WebClient webClientMock;
	
	@Test
	public void shouldReturnOk() throws JsonParseException, JsonMappingException, IOException {
		ArrayList<Comment> myComments = JSON_MAPPER.readValue(new File("src/main/resources/webservice.json"), 
				JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Comment.class));
		
		final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
		
		Mockito.when(webClientMock.get()).thenReturn(uriSpecMock);
		Mockito.when(uriSpecMock.accept(MediaType.APPLICATION_JSON)).thenReturn(headersSpecMock);
		Mockito.when(headersSpecMock.exchangeToFlux(Mockito.any()))
        .thenReturn(Flux.fromIterable(myComments));
		
		assertNotNull(dao.searchComments());
		assertEquals(myComments.get(0).getId(), dao.searchComments().blockFirst().getId());
	}
	
	
}
