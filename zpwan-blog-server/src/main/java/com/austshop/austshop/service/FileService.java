package com.austshop.austshop.service;

import com.austshop.austshop.Utils.FileUtils;
import com.austshop.austshop.Utils.MD5Utils;
import com.austshop.austshop.entity.FileInfo;
import com.austshop.austshop.mapper.FileMapper;
import com.austshop.austshop.service.impl.IFileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service(value = "fileSerice")
public class FileService implements IFileService {

    /**
     * 注入DAO层
     */
    @Autowired
    private FileMapper fileMapper;

    private static Logger logger = Logger.getLogger(FileUtils.class);

    @Override
    public FileInfo getFileInsert(MultipartFile multipartFile) {
        FileUtils fileUtils = new FileUtils();

        // 文件后缀名
        String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().indexOf('.') + 1);
        // 文件前缀名
        String suffix = MD5Utils.md5(multipartFile.getOriginalFilename());
        //  拼接成新的文件名
        String newFileName = suffix + "." + extension;

        /**
         * 进行md5校验  如果根据md5查询不到数据就执行新增
         */
        try {
            if (fileUtils.uploadFile(newFileName, multipartFile.getInputStream())) {
                logger.info("文件" + newFileName + "上传成功");
            } else {
                logger.info("文件" + newFileName + "上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInfo fileInfo = new FileInfo();
        if (fileMapper.getFileInfoByMD5(FileUtils.getFileMD5(multipartFile)) == null) {
            fileInfo.setId(UUID.randomUUID().toString().toLowerCase());
            fileInfo.setFileName(newFileName);
            fileInfo.setLength(multipartFile.getSize());
            fileInfo.setMd5(FileUtils.getFileMD5(multipartFile));
            fileInfo.setUrl("https://zpwan-yz.com/upload/" + newFileName);
            fileInfo.setCreate_time(new Date());
            fileMapper.insertFileSelective(fileInfo);
        } else {
            fileInfo = fileMapper.getFileInfoByMD5(FileUtils.getFileMD5(multipartFile));
        }
        return fileInfo;
    }
}
