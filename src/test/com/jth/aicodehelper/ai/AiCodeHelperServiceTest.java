package com.jth.aicodehelper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeHelperServiceTest {

    @Resource
    private AiCodeHelperService aiCodeHelperService;

    @Test
    void chat() {
        String result = aiCodeHelperService.chat("你好我是大帅比");
        System.out.println(result);
    }

    @Test
    void chatMemory() {
        String result = aiCodeHelperService.chat("你好我是李文涛");
        System.out.println(result);
        String chat = aiCodeHelperService.chat("我是谁");
        System.out.println(chat);
    }

    @Test
    void chatReport() {
        String userMessage = "你好我是掉毛李文涛，喜欢学习编程，帮我制定学习报告";
        AiCodeHelperService.Report report = aiCodeHelperService.chatForReport(userMessage);
        System.out.println(report);
    }
}