package org.bristolenergynetwork.retrofit.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.bristolenergynetwork.retrofit.model.Comment;
import org.bristolenergynetwork.retrofit.model.CommentUpdateDto;

public interface CommentMapper {
  //  @Insert(
  //      "insert into comment values"
  //          + " (null,#{content},#{contractorId},#{userId},#{parentCommentId},#{childCommentId})")
  //  boolean save(Comment comment);

  @Delete("delete from comment where id=#{id}")
  boolean delete(Integer id);

  @Select("select * from comment where contractor_id=#{contractorId}")
  List<Comment> show(Integer contractorId);

  @Select("select * from comment where user_id=#{userId}")
  List<Comment> findCommentsByUserId(Integer userId);

  @Update("update comment set content=#{content} WHERE id=#{commentId}")
  Boolean updateById(CommentUpdateDto commentUpdateDto);
}
