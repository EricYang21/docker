package org.bristolenergynetwork.retrofit.model;

import java.util.Objects;
import lombok.Data;

@Data
public class BrowseHistory {
  private Integer userId;
  private String link;

  public BrowseHistory(Integer userId, String link) {
    this.userId = userId;
    this.link = link;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BrowseHistory that = (BrowseHistory) o;
    return Objects.equals(userId, that.userId) && Objects.equals(link, that.link);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, link);
  }

  @Override
  public String toString() {
    return "BrowseHistory{" + "userId=" + userId + ", link='" + link + '\'' + '}';
  }
}
