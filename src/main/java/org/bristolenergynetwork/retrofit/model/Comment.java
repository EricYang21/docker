package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;

public class Comment {
  // comment
  private int id;
  private String content;
  private int contractorId;
  private int userId;
  private int parentCommentId;
  private int childCommentId;

  public Comment(
      int id,
      String content,
      int contractorId,
      int user_id,
      int parentCommentId,
      int childCommentId) {
    this.id = id;
    this.content = content;
    this.contractorId = contractorId;
    this.userId = user_id;
    this.parentCommentId = parentCommentId;
    this.childCommentId = childCommentId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getContractorId() {
    return contractorId;
  }

  public void setContractorId(int contractorId) {
    this.contractorId = contractorId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getParentCommentId() {
    return parentCommentId;
  }

  public void setParentCommentId(int parentCommentId) {
    this.parentCommentId = parentCommentId;
  }

  public int getChildCommentId() {
    return childCommentId;
  }

  public void setChildCommentId(int childCommentId) {
    this.childCommentId = childCommentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Comment comment = (Comment) o;
    return id == comment.id
        && contractorId == comment.contractorId
        && userId == comment.userId
        && parentCommentId == comment.parentCommentId
        && childCommentId == comment.childCommentId
        && Objects.equals(content, comment.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, content, contractorId, userId, parentCommentId, childCommentId);
  }

  @Override
  public String toString() {
    return "Comment{"
        + "id="
        + id
        + ", content='"
        + content
        + '\''
        + ", contractorId="
        + contractorId
        + ", user_id="
        + userId
        + ", parentCommentId="
        + parentCommentId
        + ", childCommentId="
        + childCommentId
        + '}';
  }
}
