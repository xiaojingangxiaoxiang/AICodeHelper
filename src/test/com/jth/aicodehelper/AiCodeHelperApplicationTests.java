package com.jth.aicodehelper;

import com.jth.aicodehelper.ai.AiCodeHelperService;
import com.jth.aicodehelper.ai.AiCoderHelper;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
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

    @Test
    void chatWithMessage() {
        UserMessage userMessage = UserMessage.from(
                TextContent.from("描述图片"),
                ImageContent.from("https://ts3.tc.mm.bing.net/th/id/OIP-C.NXPTMrnVEh4ROdiSFWE71wHaHa?w=250&h=250&c=8&rs=1&qlt=90&o=6&dpr=2&pid=3.1&rm=2")
        );
        aiCoderHelper.chatWithMessage(userMessage);
    }
}
