package org.bristolenergynetwork.retrofit.controller;

import org.bristolenergynetwork.retrofit.model.BrowseHistory;
import org.bristolenergynetwork.retrofit.model.ChangeAvatarDto;
import org.bristolenergynetwork.retrofit.model.ChangeBirthDto;
import org.bristolenergynetwork.retrofit.model.ChangePasswordDto;
import org.bristolenergynetwork.retrofit.model.ChangeSexDto;
import org.bristolenergynetwork.retrofit.model.ChangeUsernameDto;
import org.bristolenergynetwork.retrofit.model.IsOkResponse;
import org.bristolenergynetwork.retrofit.model.ShowUserCenterResponse;
import org.bristolenergynetwork.retrofit.model.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCenterController {
  @Autowired UserCenterService userCenterService;

  @PostMapping("/showUserCenter")
  @CrossOrigin
  public ShowUserCenterResponse show(@RequestBody UserId userIdRequest) {
    ShowUserCenterResponse show = userCenterService.show(userIdRequest);
    return show;
  }

  @PostMapping("/browserHistory")
  @CrossOrigin
  public IsOkResponse browserHistory(@RequestBody BrowseHistory browseHistory) {
    IsOkResponse isOkResponse = userCenterService.browseHistory(browseHistory);
    return isOkResponse;
  }

  @PostMapping("/changeusername")
  @CrossOrigin
  public IsOkResponse changeUsername(@RequestBody ChangeUsernameDto changeUsernameDto) {
    IsOkResponse isOkResponse = userCenterService.changeUsername(changeUsernameDto);
    return isOkResponse;
  }

  @PostMapping("/changesex")
  @CrossOrigin
  public IsOkResponse changeSex(@RequestBody ChangeSexDto changeSexDto) {
    IsOkResponse isOkResponse = userCenterService.changeSex(changeSexDto);
    return isOkResponse;
  }

  @PostMapping("/changeavatar")
  @CrossOrigin
  public IsOkResponse changeAvatar(@RequestBody ChangeAvatarDto changeAvatarDto) {
    return userCenterService.changeAvatar(changeAvatarDto);
  }

  @PostMapping("/changepassword")
  @CrossOrigin
  public IsOkResponse changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
    return userCenterService.changePassword(changePasswordDto);
  }

  @PostMapping("/changebirth")
  @CrossOrigin
  public IsOkResponse changeBirth(@RequestBody ChangeBirthDto changeBirthDto) {
    return userCenterService.changeBirth(changeBirthDto);
  }
}
