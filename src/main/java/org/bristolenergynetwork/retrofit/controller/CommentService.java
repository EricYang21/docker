package org.bristolenergynetwork.retrofit.controller;

import java.util.ArrayList;
import java.util.List;
import org.bristolenergynetwork.retrofit.mappers.CommentMapper;
import org.bristolenergynetwork.retrofit.mappers.UserMapper;
import org.bristolenergynetwork.retrofit.model.Comment;
import org.bristolenergynetwork.retrofit.model.CommentAndUser;
import org.bristolenergynetwork.retrofit.model.CommentDeleteDto;
import org.bristolenergynetwork.retrofit.model.CommentUpdateDto;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.FinalCommentShow;
import org.bristolenergynetwork.retrofit.model.Graph;
import org.bristolenergynetwork.retrofit.model.IsOkResponse;
import org.bristolenergynetwork.retrofit.model.User;
import org.bristolenergynetwork.retrofit.model.UserInComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  @Autowired CommentMapper commentMapper;
  @Autowired UserMapper userMapper;

  public boolean save(Comment comment) {
    boolean result = commentMapper.save(comment);
    return result;
  }

  public IsOkResponse delete(CommentDeleteDto commentDeleteDto) {
    boolean result = commentMapper.delete(commentDeleteDto.getCommentId());
    IsOkResponse isOkResponse = new IsOkResponse(commentDeleteDto.getUserId(), result);
    return isOkResponse;
  }

  public FinalCommentShow show(Contractor contractor) {
    Graph graph = graph(contractor);
    List<Comment> result = commentMapper.show(contractor.getId());
    List<CommentAndUser> commentAndUserList = new ArrayList<>();
    CommentAndUser commentAndUser = null;
    for (Comment comment : result) {
      int userId = comment.getUserId();
      User userById = userMapper.findUserById(userId);
      String avatar = userById.getAvatar();
      String username = userById.getUsername();
      UserInComment userInComment = new UserInComment(avatar, username);
      commentAndUser = new CommentAndUser(comment, userInComment);
      commentAndUserList.add(commentAndUser);
    }
    FinalCommentShow finalCommentShow = new FinalCommentShow(commentAndUserList, graph);
    return finalCommentShow;
  }

  public UserInComment userInComment(Comment comment) {
    int userId = comment.getUserId();
    if (userId == 0) {
      return null;
    }
    User user = userMapper.findUserById(userId);
    String avatar = user.getAvatar();
    String username = user.getUsername();
    UserInComment userInComment = new UserInComment(avatar, username);
    return userInComment;
  }

  public IsOkResponse update(CommentUpdateDto commentUpdateDto) {
    Boolean isOk = commentMapper.updateById(commentUpdateDto);
    IsOkResponse isOkResponse = new IsOkResponse(commentUpdateDto.getUserId(), isOk);
    return isOkResponse;
  }

  public Graph graph(Contractor contractor) {

    return new Graph("-2.596472", "51.4559");
  }
}
