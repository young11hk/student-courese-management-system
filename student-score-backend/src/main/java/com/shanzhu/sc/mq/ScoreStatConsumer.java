package com.shanzhu.sc.mq;

import com.shanzhu.sc.config.RabbitMQConfig;
import com.shanzhu.sc.service.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ScoreStatConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ScoreStatConsumer.class);

    @Autowired
    private UserService userService;

    /**
     * 监听成绩统计队列
     */
    @RabbitListener(queues = RabbitMQConfig.SCORE_STAT_QUEUE)
    public void handleScoreStatMessage(Map<String, Object> message) {
        logger.info("收到成绩统计消息: {}", message);

        try {
            String type = (String) message.get("type");
            Map<String, Object> data = (Map<String, Object>) message.get("data");

            if ("SCORE_STAT".equals(type)) {
                // 模拟成绩统计计算
                processScoreStat(data);
            }
        } catch (Exception e) {
            logger.error("处理成绩统计消息失败: {}", e.getMessage());
        }
    }

    /**
     * 处理成绩统计逻辑（计算班级/专业平均分等）
     */
    private void processScoreStat(Map<String, Object> data) {
        String courseId = (String) data.get("courseId");
        String profession = (String) data.get("profession");
        String grade = (String) data.get("grade");

        logger.info("开始计算成绩统计: courseId={}, profession={}, grade={}",
                    courseId, profession, grade);

        // TODO: 这里可以添加实际的统计逻辑
        // 例如：计算班级平均分、专业平均分、最高分、最低分等

        logger.info("成绩统计计算完成");
    }

    /**
     * 监听日志队列
     */
    @RabbitListener(queues = RabbitMQConfig.LOG_QUEUE)
    public void handleLogMessage(Map<String, Object> message) {
        logger.info("收到日志消息: {}", message);

        try {
            String level = (String) message.get("level");
            String msg = (String) message.get("message");
            String username = (String) message.get("username");

            // TODO: 这里可以写入文件或数据库
            logger.info("[日志] level={}, username={}, message={}", level, username, msg);

        } catch (Exception e) {
            logger.error("处理日志消息失败: {}", e.getMessage());
        }
    }
}