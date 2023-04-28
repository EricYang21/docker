package org.bristolenergynetwork.retrofit.controller;

import java.security.PrivateKey;
import org.apache.commons.lang3.StringUtils;
import org.bristolenergynetwork.retrofit.mappers.UserMapper;
import org.bristolenergynetwork.retrofit.model.IsOkResponse;
import org.bristolenergynetwork.retrofit.model.LoginDto;
import org.bristolenergynetwork.retrofit.model.SignUpDto;
import org.bristolenergynetwork.retrofit.model.User;
import org.bristolenergynetwork.retrofit.utils.BCrypt;
import org.bristolenergynetwork.retrofit.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

  @Value("${retrofit.jwt.privateKeyPath}")
  private String privateKeyPath;

  @Value("${retrofit.jwt.expire}")
  private Integer expire;

  @Autowired UserMapper userMapper;

  @Value("${retrofit.avatar}")
  private String avatar;

  public IsOkResponse login(LoginDto loginDto) throws Exception {
    PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyPath);
    if (StringUtils.isNotEmpty(loginDto.getEmail())
        && StringUtils.isNotEmpty(loginDto.getPassword())) {
      // login
      User existUser = userMapper.findIfExist(loginDto.getEmail());
      if (existUser == null) {
        return new IsOkResponse(0, false);
      }
      if (!BCrypt.checkpw(loginDto.getPassword(), existUser.getPassword())) {
        return new IsOkResponse(0, false);
      }

      return new IsOkResponse(existUser.getId(), true);
    }

    return new IsOkResponse(0, false);
  }

  public IsOkResponse save(@RequestBody SignUpDto signUpDto) {
    String salt = BCrypt.gensalt();
    String encodePwd = BCrypt.hashpw(signUpDto.getPassword(), salt);
    User user =
        new User(
            null,
            signUpDto.getEmail(),
            signUpDto.getUsername(),
            avatar,
            encodePwd,
            "male",
            "2000/1/1");
    user.setPassword(encodePwd);
    userMapper.save(user);
    Integer userId = 0;

    User u = userMapper.findUserId(user.getEmail());
    if (u != null) {
      userId = u.getId();
    }

    IsOkResponse loginOrSignUpResponse;

    if (userId == 0) {
      loginOrSignUpResponse = new IsOkResponse(0, false);
    } else {
      loginOrSignUpResponse = new IsOkResponse(userId, true);
    }

    return loginOrSignUpResponse;
  }
}
