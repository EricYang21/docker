package org.bristolenergynetwork.retrofit.controller;

import org.bristolenergynetwork.retrofit.mappers.UserMapper;
import org.bristolenergynetwork.retrofit.model.Comment;
import org.bristolenergynetwork.retrofit.model.CommentDeleteDto;
import org.bristolenergynetwork.retrofit.model.CommentUpdateDto;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.FinalCommentShow;
import org.bristolenergynetwork.retrofit.model.IsOkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
  @Autowired private CommentService commentService;
  @Autowired private UserMapper userMapper;

  @PostMapping("commentShow")
  @CrossOrigin
  public FinalCommentShow show(@RequestBody Contractor contractor) {
    FinalCommentShow show = commentService.show(contractor);
    return show;
  }

  @PostMapping("commentSave")
  @CrossOrigin
  public Boolean save(@RequestBody Comment comment) {

    return commentService.save(comment);
  }

  @RequestMapping("commentDelete")
  @CrossOrigin
  public IsOkResponse delete(@RequestBody CommentDeleteDto commentDeleteDto) {
    return commentService.delete(commentDeleteDto);
  }

  @RequestMapping("commentUpdate")
  @CrossOrigin
  public IsOkResponse update(@RequestBody CommentUpdateDto commentUpdateDto) {
    IsOkResponse update = commentService.update(commentUpdateDto);
    return update;
  }
}
