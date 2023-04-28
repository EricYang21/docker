package org.bristolenergynetwork.retrofit.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.bristolenergynetwork.retrofit.model.ChangeAvatarDto;
import org.bristolenergynetwork.retrofit.model.ChangeBirthDto;
import org.bristolenergynetwork.retrofit.model.ChangePasswordDto;
import org.bristolenergynetwork.retrofit.model.ChangeSexDto;
import org.bristolenergynetwork.retrofit.model.ChangeUsernameDto;
import org.bristolenergynetwork.retrofit.model.User;

public interface UserMapper {
  @Select("select * from user where email=#{email} limit 1")
  public User findIfExist(String email);

  @Insert(
      "insert into user values (null,#{email},#{username},#{avatar},#{password},#{sex},#{birth})")
  void save(User user);

  @Select("select * from user where email=#{email} limit 1")
  User findUserId(String email);

  @Select("select * from user where id=#{id} limit 1")
  User findUserById(Integer id);

  @Update("update user set username=#{username} WHERE id=#{userId}")
  Boolean changeUsername(ChangeUsernameDto changeUsernameDto);

  @Update("update user set sex=#{sex} WHERE id=#{userId}")
  Boolean changeSex(ChangeSexDto changeSexDto);

  @Update("update user set avatar=#{avatar} WHERE id=#{userId}")
  Boolean changeAvatar(ChangeAvatarDto changeAvatarDto);

  @Update("update user set password=#{password} WHERE id=#{userId}")
  Boolean changePassword(ChangePasswordDto changePasswordDto);

  @Update("update user set birth=#{birth} WHERE id=#{userId}")
  Boolean changeBirth(ChangeBirthDto changeBirthDto);
}
