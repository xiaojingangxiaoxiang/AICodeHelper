package com.jth.aicodehelper;

import com.jth.aicodehelper.ai.AiCoderHelper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeHelperApplicationTests {

    @Resource
    private AiCoderHelper aiCoderHelper;

    @Test
    void chat() {
        aiCoderHelper.chat("你好，你在干什么");
    }

}
