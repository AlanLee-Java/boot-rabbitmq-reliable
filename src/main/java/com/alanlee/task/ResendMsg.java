package com.alanlee.task;

import com.alanlee.common.Constant;
import com.alanlee.entity.MsgLog;
import com.alanlee.message.MessageHelper;
import com.alanlee.service.MsgLogService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务重新投递发送失败的消息
 * 如果超过最大投递次数还是失败的消息就会标记为投递失败（需要人工介入排查原因）
 * @author AlanLee
 */
@Component
@ConfigurationProperties(prefix = "reliable")
@Slf4j
public class ResendMsg {

    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 最大投递次数
    @Getter
    @Setter
    private int maxMessageTryCount;

    /**
     * 每30s拉取投递失败的消息, 重新投递
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void resend() {
        log.info("开始执行定时任务(重新投递消息)");

        List<MsgLog> msgLogs = msgLogService.selectTimeoutMsg();
        msgLogs.forEach(msgLog -> {
            String msgId = msgLog.getMsgId();
            if (msgLog.getTryCount() >= maxMessageTryCount) {
                msgLogService.updateStatus(msgId, Constant.MsgLogStatus.DELIVER_FAIL);
                log.info("超过最大重试次数, 消息投递失败, msgId: {}", msgId);
            } else {
                msgLogService.updateTryCount(msgId, msgLog.getNextTryTime());// 投递次数+1

                CorrelationData correlationData = new CorrelationData(msgId);
                rabbitTemplate.convertAndSend(msgLog.getExchange(), msgLog.getRoutingKey(), MessageHelper.objToMsg(msgLog.getMsg()), correlationData);// 重新投递

                log.info("第 " + (msgLog.getTryCount() + 1) + " 次重新投递消息");
            }
        });

        log.info("定时任务执行结束(重新投递消息)");
    }

}
