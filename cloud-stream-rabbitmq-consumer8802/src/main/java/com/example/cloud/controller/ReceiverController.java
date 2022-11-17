package com.example.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @author: ZhangX
 * @createDate: 2022/11/17
 * @description:
 */
@Slf4j
@EnableBinding(Sink.class) // 消息接收者
public class ReceiverController {

    @Value("#${server.port}")
    private String serverPort;


    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        //  message.getPayload() 接收到的消息
        log.info("消费者1号，-------------> 接收消息：{}， serverPort:{}", message.getPayload(), serverPort);
    }
}