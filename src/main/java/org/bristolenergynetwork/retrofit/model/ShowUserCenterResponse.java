package org.bristolenergynetwork.retrofit.model;

import java.util.List;
import lombok.Data;

@Data
public class ShowUserCenterResponse {

  private User user;
  private List<BrowseHistory> browseHistory;
  private List<Comment> comments;

  public ShowUserCenterResponse(
      User user, List<BrowseHistory> browseHistory, List<Comment> comments) {
    this.user = user;
    this.browseHistory = browseHistory;
    this.comments = comments;
  }
}
