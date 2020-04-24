package com.alanlee.service.impl;

import com.alanlee.common.Constant;
import com.alanlee.common.ResponseCode;
import com.alanlee.common.ServerResponse;
import com.alanlee.config.RabbitConfig;
import com.alanlee.dto.Mail;
import com.alanlee.mapper.MsgLogMapper;
import com.alanlee.entity.MsgLog;
import com.alanlee.message.MessageHelper;
import com.alanlee.service.TestService;
import com.alanlee.util.RandomUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 通过mq发送邮件
     * 模拟微服务推送消息给另一个微服务消费（后续完善此业务逻辑）
     * @param mail
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse send(Mail mail) {
        // 业务无关消息ID，这里采用UUID32位
        String msgId = RandomUtil.UUID32();
        mail.setMsgId(msgId);

        // 假设此处执行的是某个微服务的业务,然后要把消息推送到其他的微服务消费
        // 那么可以通过@Transactional注解保证业务和消息在一个事务中落地到数据库
        // xxx微服务xxx业务执行

        // 消息落地到数据库（在一个事务当中）
        MsgLog msgLog = new MsgLog(msgId, mail, Constant.SendMailQueue.MAIL_EXCHANGE_NAME, Constant.SendMailQueue.MAIL_ROUTING_KEY_NAME);
        msgLogMapper.insert(msgLog);// 消息入库

        // 生产消息并发送
        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(Constant.SendMailQueue.MAIL_EXCHANGE_NAME, Constant.SendMailQueue.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);// 发送消息

        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }

}
