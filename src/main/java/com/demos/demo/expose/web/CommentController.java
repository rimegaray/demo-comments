package com.demos.demo.expose.web;

import com.demos.demo.comments.business.ICommentService;
import com.demos.demo.comments.model.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class CommentController {

	@Autowired
	private ICommentService service;
	
	@GetMapping("comments")
	public Mono<Data> searchComments(){
		return service.searchComments();
	}
}
