package com.alanlee.service;

import com.alanlee.common.ServerResponse;
import com.alanlee.dto.Mail;

public interface TestService {

    ServerResponse send(Mail mail);

}
