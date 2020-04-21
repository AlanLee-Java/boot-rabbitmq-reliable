package com.alanlee.mapper;


import com.alanlee.entity.MsgLog;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MsgLogMapper {

    void insert(MsgLog msgLog);

    void updateStatus(MsgLog msgLog);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(MsgLog msgLog);

    MsgLog selectByPrimaryKey(String msgId);

}
