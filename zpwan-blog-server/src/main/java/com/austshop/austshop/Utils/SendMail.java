package com.austshop.austshop.Utils;


import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SendMail {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    private final String from = "wanzhipengx@163.com";

    private final String to = "892172140@qq.com";

    @Async
    public void handleMailSend(JavaMailSenderImpl javaMailSender, String subject, String id, String description) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setTo(to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            mimeMessageHelper.setFrom(from);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            mimeMessageHelper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        String href = "http://zpwan-yz.com/article/" + id;
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>宝矬宝矬</h1><p>" + description + "点<a href=" + href + ">这里</a></p></body>");
        sb.append("</html>");

        // 启用html
        try {
            mimeMessageHelper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 发送邮件
        javaMailSender.send(mimeMessage);

        logger.debug("邮件发送成功,from:wanzhipengx@163.com" + from + "to:" + to + "content:" + sb.toString());
    }
}
