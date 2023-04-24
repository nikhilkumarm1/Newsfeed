package com.fun.newsfeed.commands;

import com.fun.newsfeed.common.ColorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CommandRegistry {

  private final List<UserCommand> userCommandList;
  private final Set<String> commandTypeSet;

  @Autowired
  public CommandRegistry(List<UserCommand> userCommandList) {
    this.userCommandList = userCommandList;
    commandTypeSet = userCommandList.stream().map(UserCommand::supportedCommandType).map(CommandType::getCommandType).collect(Collectors.toSet());
  }

  public void execute(String input) {
    for (UserCommand userCommand : userCommandList) {
      if (userCommand.canExecute(input)) {
        userCommand.execute();
        break;
      }
    }
  }

  public Set<String> supportedCommandTypes() {
    return commandTypeSet;
  }
}
