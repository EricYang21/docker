package org.bristolenergynetwork.retrofit.controller;

import org.bristolenergynetwork.retrofit.model.IsOkResponse;
import org.bristolenergynetwork.retrofit.model.LoginDto;
import org.bristolenergynetwork.retrofit.model.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired UserService userService;

  @PostMapping("/signUp")
  @CrossOrigin
  public IsOkResponse signUp(@RequestBody SignUpDto signUpDTO) {
    IsOkResponse save = userService.save(signUpDTO);
    return save;
  }

  @PostMapping("/login")
  @CrossOrigin
  public IsOkResponse login(@RequestBody LoginDto loginDto) throws Exception {
    return userService.login(loginDto);
  }
}
