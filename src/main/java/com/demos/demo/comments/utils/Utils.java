package com.demos.demo.comments.utils;

import com.demos.demo.comments.model.Comment;

public class Utils {
	
  public static String transformData(Comment comment) {
	  return new StringBuilder()
			  .append(comment.getPostId())
			  .append("|")
			  .append(comment.getId() )
			  .append("|")
			  .append(comment.getEmail())
			  .toString();
  }
  
}
