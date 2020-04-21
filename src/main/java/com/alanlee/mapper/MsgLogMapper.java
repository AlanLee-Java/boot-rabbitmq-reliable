package com.alanlee.mapper;


import com.alanlee.entity.MsgLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 映射类：消息日志
 */
public interface MsgLogMapper {

    /**
     * 插入消息
     *
     * @param msgLog
     */
    void insert(MsgLog msgLog);

    /**
     * 修改消息状态
     *
     * @param msgLog
     */
    void updateStatus(MsgLog msgLog);

    List<MsgLog> selectTimeoutMsg();

    /**
     * 修改消息的重试次数和下一次投递时间
     *
     * @param msgLog
     */
    void updateTryCount(MsgLog msgLog);

    /**
     * 根据消息id查询消息
     *
     * @param msgId
     * @return
     */
    MsgLog selectByPrimaryKey(String msgId);

}
