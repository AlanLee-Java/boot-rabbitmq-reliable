package com.alanlee.entity;

import com.alanlee.common.Constant;
import com.alanlee.util.JodaTimeUtil;
import com.alanlee.util.JsonUtil;
import lombok.*;

import java.util.Date;

/**
 * 实体类：消息日志
 *
 * @author AlanLee
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MsgLog {

    /**
     * 消息唯一标识
     */
    private String msgId;
    /**
     * 消息体, json格式化
     */
    private String msg;
    /**
     * 交换机
     */
    private String exchange;
    /**
     * 路由键
     */
    private String routingKey;
    /**
     * 状态: 0投递中 1投递成功 2投递失败 3已消费
     */
    private Integer status;
    /**
     * 重试次数
     */
    private Integer tryCount;
    /**
     * 下一次重试时间
     */
    private Date nextTryTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建消息对象
     * @param msgId 消息id
     * @param msg 消息体
     * @param exchange 交换机
     * @param routingKey 路由键
     */
    public MsgLog(String msgId, Object msg, String exchange, String routingKey) {
        this.msgId = msgId;
        this.msg = JsonUtil.objToStr(msg);
        this.exchange = exchange;
        this.routingKey = routingKey;

        this.status = Constant.MsgLogStatus.DELIVERING;
        this.tryCount = 0;

        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
        this.nextTryTime = (JodaTimeUtil.plusMinutes(date, 1));
    }
}
