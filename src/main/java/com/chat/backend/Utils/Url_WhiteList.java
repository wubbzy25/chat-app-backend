package com.chat.backend.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Url_WhiteList {

    @Bean
    public List<String> getUrls() {
        return Arrays.asList(
                "/api/v1/auth/login",
                "/chat"
        );
    }

}
