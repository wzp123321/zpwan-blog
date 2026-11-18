package com.austshop.austshop.Utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.MessageDigest;

/**
 * 计算文件md5值
 */
public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    // ftp连接客户端
    public static FTPClient ftpClient = null;
    //  连接信息全部由环境变量注入，仓库内不保存明文凭据
    private static final String ftpUserName = System.getenv().getOrDefault("FTP_USERNAME", "");
    private static final String ftpPassword = System.getenv().getOrDefault("FTP_PASSWORD", "");
    private static final String ftpHost = System.getenv().getOrDefault("FTP_HOST", "127.0.0.1");
    private static final int ftpPort = Integer.parseInt(System.getenv().getOrDefault("FTP_PORT", "21"));
    private static final String ftpPath = System.getenv().getOrDefault("FTP_PATH", "/home/upload");

    //    获取文件的md5值
    public static String getFileMD5(MultipartFile file) {
        try {
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            return hashString;
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return null;
    }

    /**
     * 初始化ftp服务器
     */
    public static void initFtpClient(String ftpHost, String ftpUserName, String ftpPassword, Integer ftpPort) {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            //连接ftp服务器
            ftpClient.connect(ftpHost, ftpPort);
            //登录ftp服务器
            ftpClient.login(ftpUserName, ftpPassword);
            //登录服务器响应码
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("connect failed...ftp服务器:" + ftpHost + ":" + ftpPort);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(String fileName, InputStream inputStream) {
        // 设置开关
        boolean flag = true;
        try {
            initFtpClient(ftpHost, ftpUserName, ftpPassword, ftpPort);

            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);

            ftpClient.enterLocalPassiveMode();

            ftpClient.makeDirectory(ftpPath);

            ftpClient.setControlEncoding("utf-8");

            ftpClient.storeFile(fileName, inputStream);

            inputStream.close();

            ftpClient.logout();
            logger.info("上传文件成功");
        } catch (Exception e) {
            flag = false;
            logger.error("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}
