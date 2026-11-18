package com.austshop.austshop.controller;

import com.austshop.austshop.Utils.JsonWebTokenUtils;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *文件上传
 * zpwan
 * 2019/3/25
 */
@Api(description = "文件模块")
@RestController
@RequestMapping(value="/blogManage")
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @ApiOperation(value="文件上传")
    @PostMapping(value="/filemodule/web/upload")
    public ResultResponse handleUpload(
            @RequestParam(value = "file", required = true) MultipartFile file,
            HttpServletRequest request
    ) {
        if (file.isEmpty()) {
            return new ResultResponse(501, "文件为空，请重新上传");
        } else {
            return new ResultResponse(200, "上传成功", fileService.getFileInsert(file));
        }
    }

}
