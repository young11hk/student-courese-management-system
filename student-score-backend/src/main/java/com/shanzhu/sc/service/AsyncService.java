package com.shanzhu.sc.service;

import com.shanzhu.sc.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AsyncService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送成绩统计消息（异步处理）
     */
    public void sendScoreStatMessage(Map<String, Object> scoreData) {
        Map<String, Object> message = new HashMap<>();
        message.put("type", "SCORE_STAT");
        message.put("data", scoreData);
        message.put("timestamp", new Date());

        rabbitTemplate.convertAndSend(
            RabbitMQConfig.SMS_EXCHANGE,
            RabbitMQConfig.SCORE_STAT_ROUTING_KEY,
            message
        );
    }

    /**
     * 发送日志消息（异步记录）
     */
    public void sendLogMessage(String level, String message, String username) {
        Map<String, Object> logData = new HashMap<>();
        logData.put("level", level);
        logData.put("message", message);
        logData.put("username", username);
        logData.put("timestamp", new Date());

        rabbitTemplate.convertAndSend(
            RabbitMQConfig.SMS_EXCHANGE,
            RabbitMQConfig.LOG_ROUTING_KEY,
            logData
        );
    }
}