package com.el.handler;

import com.el.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author:superJar
 * @date:2021/1/1
 * @time:14:49
 * @details:
 */
public class ElSessionExpireHandler implements SessionInformationExpiredStrategy {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(objectMapper.writeValueAsString(Result.fail("有其他人在其他地方登陆了该账号，你要被迫退出！")));
    }
}
