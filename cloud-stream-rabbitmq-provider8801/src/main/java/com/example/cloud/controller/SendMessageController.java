package com.example.cloud.controller;

import com.example.cloud.Service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: ZhangX
 * @createDate: 2022/11/17
 * @description:
 */

@RestController
@Slf4j
public class SendMessageController {
    @Resource
    private MessageProvider messageProvider;

    @GetMapping("/send")
    public String send() {
        return messageProvider.send();
    }
}
