package com.demos.demo.comments.dao;

import com.demos.demo.comments.model.Comment;

import reactor.core.publisher.Flux;

public interface ICommentDao {
	public Flux<Comment> searchComments();
}
