package com.demos.demo.comments.business;

import com.demos.demo.comments.model.Data;

import reactor.core.publisher.Mono;

public interface ICommentService {
	public Mono<Data> searchComments();
}
