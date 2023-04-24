package com.fun.newsfeed.commands;

import com.fun.newsfeed.common.ColorConstants;
import com.fun.newsfeed.exception.NotValidException;
import com.fun.newsfeed.post.PostController;
import com.fun.newsfeed.post.dto.BinaryDto;
import com.fun.newsfeed.post.dto.ContentDto;
import com.fun.newsfeed.post.dto.TextDto;
import com.fun.newsfeed.post.dto.UserPostDto;
import com.fun.newsfeed.post.exception.PostFailureException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ReplyCommand implements UserCommand {

  private final PostController postController;

  public ReplyCommand(PostController postController) {
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
    long parentId = scanner.nextLong();
    scanner.nextLine();
    System.out.println("Enter reply text*");
    String text = scanner.nextLine();
    List<ContentDto> contentList = new ArrayList<>();
    contentList.add(new TextDto(text));
    System.out.println("Enter Image Link");
    String link = scanner.nextLine();
    System.out.println("Enter Image Caption");
    String tag = scanner.nextLine();
    contentList.add(new BinaryDto(link, tag));
    try {
      long postId = postController.addUserPost(UserPostDto.newBuilder().withContentList(contentList).withParentId(parentId).build());
      System.out.println(ColorConstants.ANSI_GREEN
          + "Successfully commented on postId/commentId, with Id: "+ postId + ColorConstants.ANSI_RESET);
    } catch (PostFailureException e) {
      e.printStackTrace();
      System.out.println(ColorConstants.ANSI_RED + "User Post Failed" + ColorConstants.ANSI_RESET);
    } catch (NotValidException e) {
      e.printStackTrace();
      System.out.println(ColorConstants.ANSI_RED + "Validation Error" + ColorConstants.ANSI_RESET);
    }
  }
  @Override
  public CommandType supportedCommandType() {
    return CommandType.REPLY;
  }
}
