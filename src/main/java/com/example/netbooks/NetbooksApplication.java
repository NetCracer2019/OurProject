package com.example.netbooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.netbooks.services.EmailSender;

@SpringBootApplication
public class NetbooksApplication implements CommandLineRunner {

    @Autowired
    EmailSender emailSender;

    public static void main(String[] args) {
        SpringApplication.run(NetbooksApplication.class, args);
    }

    @Override
    public void run(String... args) {
        emailSender.sendMessage("sashaffg@gmail.com", "ret", "bet");
        System.out.println("gg");

    }

}
