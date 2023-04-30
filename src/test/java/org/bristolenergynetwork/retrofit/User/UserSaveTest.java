package org.bristolenergynetwork.retrofit.User;

import org.bristolenergynetwork.retrofit.controller.UserCenterController;
import org.bristolenergynetwork.retrofit.model.ChangeUsernameDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSaveTest {
  @Autowired UserCenterController userCenterController;

  @Test
  public void testsave() {
    ChangeUsernameDto cat = new ChangeUsernameDto("cat", 5);
    userCenterController.changeUsername(cat);
  }
}
