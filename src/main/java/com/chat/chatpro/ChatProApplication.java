package com.chat.chatpro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@Slf4j
public class ChatProApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatProApplication.class, args);
    }

}
