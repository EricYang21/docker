package org.bristolenergynetwork.retrofit.controller;

import java.util.List;
import org.bristolenergynetwork.retrofit.mappers.BrowseHistoryMapper;
import org.bristolenergynetwork.retrofit.mappers.CommentMapper;
import org.bristolenergynetwork.retrofit.mappers.UserMapper;
import org.bristolenergynetwork.retrofit.model.BrowseHistory;
import org.bristolenergynetwork.retrofit.model.ChangeAvatarDto;
import org.bristolenergynetwork.retrofit.model.ChangeBirthDto;
import org.bristolenergynetwork.retrofit.model.ChangePasswordDto;
import org.bristolenergynetwork.retrofit.model.ChangeSexDto;
import org.bristolenergynetwork.retrofit.model.ChangeUsernameDto;
import org.bristolenergynetwork.retrofit.model.Comment;
import org.bristolenergynetwork.retrofit.model.IsOkResponse;
import org.bristolenergynetwork.retrofit.model.ShowUserCenterResponse;
import org.bristolenergynetwork.retrofit.model.User;
import org.bristolenergynetwork.retrofit.model.UserId;
import org.bristolenergynetwork.retrofit.utils.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCenterService {
  @Autowired UserMapper userMapper;
  @Autowired BrowseHistoryMapper browseHistoryMapper;
  @Autowired CommentMapper commentMapper;

  public ShowUserCenterResponse show(UserId userIdRequest) {
    Integer userId = userIdRequest.getUserId();
    User user = userMapper.findUserById(userId);
    List<BrowseHistory> historyById = browseHistoryMapper.findHistoryById(userId);
    System.out.println("historyById = " + historyById);
    List<Comment> commentsByUserId = commentMapper.findCommentsByUserId(userId);

    ShowUserCenterResponse showUserCenterResponse =
        new ShowUserCenterResponse(user, historyById, commentsByUserId);

    return showUserCenterResponse;
  }

  public IsOkResponse changeUsername(ChangeUsernameDto changeUsernameDto) {
    Integer userId = changeUsernameDto.getUserId();
    userMapper.changeUsername(changeUsernameDto);
    Boolean isOk = true;
    return new IsOkResponse(userId, isOk);
  }

  public IsOkResponse changeSex(ChangeSexDto changeSexDto) {
    Integer userId = changeSexDto.getUserId();
    Boolean isOk = userMapper.changeSex(changeSexDto);
    return new IsOkResponse(userId, isOk);
  }

  public IsOkResponse changePassword(ChangePasswordDto changePasswordDto) {
    String password = changePasswordDto.getPassword();
    String salt = BCrypt.gensalt();
    String encodePwd = BCrypt.hashpw(password, salt);
    Integer userId = changePasswordDto.getUserId();
    Boolean isOk = userMapper.changePassword(new ChangePasswordDto(encodePwd, userId));
    return new IsOkResponse(userId, isOk);
  }

  public IsOkResponse changeBirth(ChangeBirthDto changeBirthDto) {
    Integer userId = changeBirthDto.getUserId();
    Boolean isOk = userMapper.changeBirth(changeBirthDto);
    return new IsOkResponse(userId, isOk);
  }

  public IsOkResponse changeAvatar(ChangeAvatarDto changeAvatarDto) {
    Integer userId = changeAvatarDto.getUserId();
    Boolean isOk = userMapper.changeAvatar(changeAvatarDto);
    return new IsOkResponse(userId, isOk);
  }

  public IsOkResponse browseHistory(BrowseHistory browseHistory) {
    Boolean save = browseHistoryMapper.save(browseHistory);
    return new IsOkResponse(browseHistory.getUserId(), save);
  }
}
