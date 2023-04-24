package com.fun.newsfeed.post.exception;

public class PostFailureException extends Exception {
  public PostFailureException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
