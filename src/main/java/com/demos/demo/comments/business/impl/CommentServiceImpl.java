package com.demos.demo.comments.business.impl;

import com.demos.demo.comments.business.ICommentService;
import com.demos.demo.comments.dao.ICommentDao;
import com.demos.demo.comments.model.Data;
import com.demos.demo.comments.utils.Utils;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class CommentServiceImpl implements ICommentService {

  @Autowired
  private ICommentDao dao;
  
  @Override
  public Mono<Data> searchComments() {
    return dao.searchComments()
        .map(comment -> Utils.transformData(comment))
        .collect(Collectors.toList())
        .map(data ->  new Data(data));
  }

}
