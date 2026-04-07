package com.shanzhu.sc.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // 交换机名称
    public static final String SMS_EXCHANGE = "sms.exchange";

    // 队列名称
    public static final String SCORE_STAT_QUEUE = "sms.score.stat.queue";
    public static final String LOG_QUEUE = "sms.log.queue";

    // 路由键
    public static final String SCORE_STAT_ROUTING_KEY = "sms.score.stat";
    public static final String LOG_ROUTING_KEY = "sms.log";

    // 交换机
    @Bean
    public DirectExchange smsExchange() {
        return new DirectExchange(SMS_EXCHANGE);
    }

    // 成绩统计队列
    @Bean
    public Queue scoreStatQueue() {
        return QueueBuilder.durable(SCORE_STAT_QUEUE).build();
    }

    // 日志队列
    @Bean
    public Queue logQueue() {
        return QueueBuilder.durable(LOG_QUEUE).build();
    }

    // 绑定交换机和队列 - 成绩统计
    @Bean
    public Binding scoreStatBinding(Queue scoreStatQueue, DirectExchange smsExchange) {
        return BindingBuilder.bind(scoreStatQueue).to(smsExchange).with(SCORE_STAT_ROUTING_KEY);
    }

    // 绑定交换机和队列 - 日志
    @Bean
    public Binding logBinding(Queue logQueue, DirectExchange smsExchange) {
        return BindingBuilder.bind(logQueue).to(smsExchange).with(LOG_ROUTING_KEY);
    }

    // 消息转换器
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}