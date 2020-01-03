package com.guineap_pig_329.guinea_pig.Test;

import com.guineap_pig_329.guinea_pig.GuineaPigApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= GuineaPigApplication.class)
public class My163MailTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Test
    public void testSendSimple() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo("734780178@qq.com");
        message.setSubject("标题：测试标题");
        message.setText("测试内容部份");
        javaMailSender.send(message);
    }
}
