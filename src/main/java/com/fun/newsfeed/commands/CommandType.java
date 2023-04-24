package com.fun.newsfeed.commands;

import lombok.Getter;

@Getter
public enum CommandType {
  SIGNUP("signup"), LOGIN("login"), POST("post"), FOLLOW("follow"), REPLY("reply"), VOTE("vote"), SHOW_NEWS_FEED("show_feed"), LOGOUT("logout") ;
  private String commandType;

  CommandType(String commandType) {
    this.commandType = commandType;
  }

}
