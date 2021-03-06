package com.demos.demo.comments.dao.impl;

import com.demos.demo.comments.dao.ICommentDao;
import com.demos.demo.comments.exception.ApiException;
import com.demos.demo.comments.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Component
public class CommentDaoImpl implements ICommentDao{

  @Autowired
  private WebClient webClient;
  
  @Override
  public Flux<Comment> searchComments() {
    return webClient.get()
     .accept(MediaType.APPLICATION_JSON)
     .exchangeToFlux(response -> {
       if (response.statusCode().equals(HttpStatus.OK)) {
         return response.bodyToFlux(Comment.class);
       } else {
         return Flux.error(ApiException.builderException(new Throwable()));
       }
     });
  }

}
