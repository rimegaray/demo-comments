package com.demos.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demos.demo.comments.exception.ApiException;
import com.demos.demo.comments.exception.ErrorHandler;
import com.demos.demo.comments.exception.ErrorInfo;
import com.demos.demo.expose.web.CommentController;

import reactor.test.StepVerifier;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, 
    properties = "config.base.url = https://jsonplaceholder.typicode.com/otro")
public class ControllerTestException {

	@Autowired
	CommentController controller;
	
	@Autowired
	ErrorHandler handler;

	@Test
	public void shouldReturnApiExceptionWhenUrlIsNoOk() {
	  StepVerifier.create(controller.searchComments())
	    .expectError(ApiException.class)
	    .verify();
	}
	
	@Test
	public void testHanlderException() {
		assertNotNull(handler.methodArgumentNotValidException(new ApiException("T004")));
	}
	
	@Test
	public void testErrorInfo() {
		ErrorInfo errorInfo = new ErrorInfo("T001", HttpStatus.ACCEPTED.value(), "Test");
		assertEquals("T001", errorInfo.getCode());
		assertEquals(HttpStatus.ACCEPTED.value(), errorInfo.getStatusCode());
		assertEquals("Test", errorInfo.getMessage());
	}
	
	
}
