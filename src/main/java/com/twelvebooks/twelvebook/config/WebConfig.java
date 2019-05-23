package com.twelvebooks.twelvebook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

    @Autowired
    WebClient.Builder webClientBuild;

    @Bean
    public WebClient webClient(){
        WebClient webClient = webClientBuild.baseUrl("https://dapi.kakao.com").build();
       return webClient;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
