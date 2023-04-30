package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;
import lombok.Data;

@Data
public class User {
  private Integer id;

  private String email;
  private String username;
  private String avatar;
  private String password;

  private String sex;
  private String birth;

  public User(
      Integer id,
      String email,
      String username,
      String avatar,
      String password,
      String sex,
      String birth) {
    this.id = id;
    this.email = email;
    this.username = username;
    this.avatar = avatar;
    this.password = password;
    this.sex = sex;
    this.birth = birth;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getBirth() {
    return birth;
  }

  public void setBirth(String birth) {
    this.birth = birth;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id)
        && Objects.equals(email, user.email)
        && Objects.equals(username, user.username)
        && Objects.equals(avatar, user.avatar)
        && Objects.equals(password, user.password)
        && Objects.equals(sex, user.sex)
        && Objects.equals(birth, user.birth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, username, avatar, password, sex, birth);
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", email='"
        + email
        + '\''
        + ", username='"
        + username
        + '\''
        + ", avatar='"
        + avatar
        + '\''
        + ", password='"
        + password
        + '\''
        + ", sex='"
        + sex
        + '\''
        + ", birth='"
        + birth
        + '\''
        + '}';
  }
}
