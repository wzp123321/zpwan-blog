package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IFileService {

  FileInfo getFileInsert(MultipartFile file);
}
