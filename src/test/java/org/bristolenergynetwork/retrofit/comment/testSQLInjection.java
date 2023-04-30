package org.bristolenergynetwork.retrofit.comment;

import java.sql.SQLException;
import org.bristolenergynetwork.retrofit.controller.CommentController;
import org.bristolenergynetwork.retrofit.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testSQLInjection {
  @Autowired CommentController commentController;

  @Test
  public void testSave() throws SQLException {
    Boolean sqlinjection = commentController.save(new Comment(1, "sqlinjection", 1, 1, 1, 1));
    System.out.println("sqlinjection = " + sqlinjection);
  }
}
