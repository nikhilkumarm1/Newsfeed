package com.fun.newsfeed.commands;

import com.fun.newsfeed.common.ColorConstants;
import com.fun.newsfeed.exception.NotValidSessionException;
import com.fun.newsfeed.post.PostController;
import com.fun.newsfeed.post.dto.BinaryDto;
import com.fun.newsfeed.post.dto.TextDto;
import com.fun.newsfeed.post.dto.UserPostDto;
import com.fun.newsfeed.post.exception.PostFailureException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class VoteCommand implements UserCommand {
  private final PostController postController;

  public VoteCommand(PostController postController) {
    this.postController = postController;
  }

  @Override
  public boolean canExecute(String input) {
    return supportedCommandType().getCommandType().equals(input);
  }
  @Override
  public void execute() {
    Scanner scanner = new Scanner(System.in);
    System.out.print(ColorConstants.ANSI_BLUE);
    System.out.println("Enter parent post/reply Id*");
    long postId = scanner.nextLong();
    scanner.nextLine();
    System.out.println("Did you like it? (true for UpVote, false for DownVote)*");
    String text = scanner.nextLine();

    try {
      postController.vote(postId, Boolean.valueOf(text));
      System.out.println(ColorConstants.ANSI_GREEN
          + "Successfully commented on postId/commentId, with Id: "+ postId + ColorConstants.ANSI_RESET);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(ColorConstants.ANSI_RED + "Voting Post Failed" + ColorConstants.ANSI_RESET);
    }
  }

  @Override
  public CommandType supportedCommandType() {
    return CommandType.VOTE;
  }
}
