package com.alanlee.controller;

import com.alanlee.common.ServerResponse;
import com.alanlee.dto.Mail;
import com.alanlee.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/send")
    public ServerResponse sendMail(@Valid Mail mail) {
        return testService.send(mail);
    }

}
