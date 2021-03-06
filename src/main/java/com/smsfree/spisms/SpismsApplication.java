package com.smsfree.spisms;

import com.smsfree.spisms.smsconnections.SmsConnections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@SpringBootApplication
public class SpismsApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpismsApplication.class, args);
        SmsConnections con = new SmsConnections();
        con.openSessionConnections("atmusinf_sms");
    }
}
