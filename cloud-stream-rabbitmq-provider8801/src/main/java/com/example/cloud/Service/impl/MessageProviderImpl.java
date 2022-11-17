package com.example.cloud.Service.impl;

import com.example.cloud.Service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author: ZhangX
 * @createDate: 2022/11/17
 * @description:
 */
@Slf4j
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {
    @Resource
    private MessageChannel output;
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("*************serial"+serial);
        return serial;
    }
}
