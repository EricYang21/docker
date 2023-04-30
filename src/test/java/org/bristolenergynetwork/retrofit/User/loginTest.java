package org.bristolenergynetwork.retrofit.User;

import org.bristolenergynetwork.retrofit.controller.UserController;
import org.bristolenergynetwork.retrofit.model.IsOkResponse;
import org.bristolenergynetwork.retrofit.model.SignUpDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class loginTest {
  @Autowired UserController userController;

  @Test
  public void test() throws Exception {
    IsOkResponse isOkResponse = userController.signUp(new SignUpDto("", "", ""));
    System.out.println("isOkResponse = " + isOkResponse);
  }
}
