package com.alanlee.common;

public class Constant {

    /**
     * 消息状态常量
     */
    public interface MsgLogStatus {
        Integer DELIVERING = 0;// 消息投递中
        Integer DELIVER_SUCCESS = 1;// 投递成功
        Integer DELIVER_FAIL = 2;// 投递失败
        Integer CONSUMED_SUCCESS = 3;// 已消费
    }

    /**
     * 发送邮件消息队列常量
     */
    public interface SendMailQueue {
        String MAIL_QUEUE_NAME = "mail.queue";
        String MAIL_EXCHANGE_NAME = "mail.exchange";
        String MAIL_ROUTING_KEY_NAME = "mail.routing.key";
    }

}
