package com.austshop.austshop;

import com.austshop.austshop.Utils.JsonWebTokenUtils;
import com.austshop.austshop.Utils.SendMail;
import com.austshop.austshop.mapper.AdminMapper;
import com.austshop.austshop.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AustshopApplicationTests {


    @Autowired
    DataSource dataSource;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void contextLoads() {

        System.out.println(dataSource.getClass());
        System.out.println("测试时2");
        System.out.println("测试数据库" + adminMapper.getAdminList(0, 10));
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            System.out.println(conn);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSendTextMail() {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        // 设置收件人，寄件人
//        simpleMailMessage.setTo(new String[]{"892172140@qq.com"});
//        simpleMailMessage.setFrom("wanzhipengx@163.com");
//        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
//        simpleMailMessage.setText("宝矬，宝矬");
//        // 发送邮件
//        mailSender.send(simpleMailMessage);
//
//        System.out.println("邮件已发送");
    }

    @Test
    public void handleMailHtml() {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//        try {
//            mimeMessageHelper.setTo("1421209422@qq.com");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        try {
//            mimeMessageHelper.setFrom("wanzhipengx@163.com");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        try {
//            mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【HTML】");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("<html><head></head>");
//        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
//        sb.append("</html>");
//
//        // 启用html
//        try {
//            mimeMessageHelper.setText(sb.toString(), true);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        // 发送邮件
//        mailSender.send(mimeMessage);
//
//        System.out.println("邮件已发送");
    }

    @Test
    public void handleTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTime().getTime());
    }
}

