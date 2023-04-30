package org.bristolenergynetwork.retrofit.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Api(tags = "FileController")
@Slf4j
public class FileController {

  @Autowired FileService fileService;
  @Autowired UserCenterController userCenterController;

  @PostMapping("/upload")
  @CrossOrigin
  public String upload(@RequestParam("file") MultipartFile file) throws Exception {
    String upload = fileService.upload(file);
    return upload;
  }
}
