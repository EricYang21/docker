package org.bristolenergynetwork.retrofit.controller;

import java.io.File;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  String upload(MultipartFile file) throws Exception;

  String upload(File file) throws Exception;

  String upload(InputStream inputStream, String originalFileName) throws Exception;

  String upload(byte[] bytes, String originalFileName) throws Exception;
}
