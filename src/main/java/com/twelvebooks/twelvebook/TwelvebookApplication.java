package com.twelvebooks.twelvebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAspectJAutoProxy
@EnableScheduling
//@EnableAspectAutoProxy를 명시하면 Spring AOP를 사용하기 위한 첫 준비가 끝난다.
//@EnableAspectAutoProxy은 XML 기반의 ApplicationContext 설정에서의 <aop:aspectj-autoproxy />와 동일한 기능을 한다.
//@EnableScheduling을 명시하면 @Scheduled를 사용하기 위한 준비가 끝난다.
public class TwelvebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwelvebookApplication.class, args);
    }

}