package org.bristolenergynetwork.retrofit.User;

import org.bristolenergynetwork.retrofit.controller.UserCenterController;
import org.bristolenergynetwork.retrofit.model.ChangeAvatarDto;
import org.bristolenergynetwork.retrofit.model.ChangeBirthDto;
import org.bristolenergynetwork.retrofit.model.ChangePasswordDto;
import org.bristolenergynetwork.retrofit.model.ChangeSexDto;
import org.bristolenergynetwork.retrofit.model.ChangeUsernameDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class changefunctionTest {
  @Autowired UserCenterController userCenterController;

  @Test
  public void username() {
    ChangeUsernameDto cat = new ChangeUsernameDto("cat", 5);
    userCenterController.changeUsername(cat);
  }

  @Test
  public void sex() {
    ChangeSexDto change = new ChangeSexDto("female", 5);
    userCenterController.changeSex(change);
  }

  @Test
  public void avatar() {
    ChangeAvatarDto change =
        new ChangeAvatarDto(
            "https://avatarspe.oss-cn-beijing.aliyuncs.com/2023/04/24/cc5l8ignlctteu70u879as0sluzuvgpho7upjsucrlq29fbi7cb350ed0e9dcd9ba5908b15eac29443.jpg",
            5);
    userCenterController.changeAvatar(change);
  }

  @Test
  public void password() {
    ChangePasswordDto change = new ChangePasswordDto("124", 5);
    userCenterController.changePassword(change);
  }

  @Test
  public void birth() {
    ChangeBirthDto change = new ChangeBirthDto("124", 5);
    userCenterController.changeBirth(change);
  }

  @Test
  public void show() {
    //    UserIdRequest userIdRequest = new UserIdRequest(5);
    //    userCenterController.show(userIdRequest);
  }
}
