package com.fun.newsfeed.commands;

public interface UserCommand {
  boolean canExecute(String input);
  void execute();
  CommandType supportedCommandType();
}
