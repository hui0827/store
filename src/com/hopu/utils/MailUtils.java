package com.hopu.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    public static void sendMail(String to,String code) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.qq.com");
        properties.put("mail.smtp.port","25");
        properties.put("mail.smtp.auth","true");
        // 创建连接对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1162375129@qq.com", "pkhbpmawmpacifhh\n");
            }
        });
        // 创建邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人
        message.setFrom(new InternetAddress("1162375129@qq.com"));
        // 设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 设置标题
        message.setSubject("来自商城注册的激活邮件");
        // 设置正文
        message.setContent("<h1>来自商城注册的激活邮件，点击链接激活账号：</h1><h3><a href='http://192.168.1.103:8080/study/user?code="+code+"&&method=active'>http://192.168.138.1:8080/study/active?code="+code+"</a></h3>", "text/html;charset=utf-8");
        // 发送邮件
        Transport.send(message);
    }
}
