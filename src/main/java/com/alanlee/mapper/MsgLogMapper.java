package com.alanlee.mapper;


import com.alanlee.entity.MsgLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 映射类：消息日志
 */
public interface MsgLogMapper {

    /**
     * 插入消息日志
     * @param msgLog
     */
    void insert(MsgLog msgLog);

    /**
     * 修改消息日志状态
     * @param msgLog
     */
    void updateStatus(MsgLog msgLog);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(MsgLog msgLog);

    MsgLog selectByPrimaryKey(String msgId);

}
