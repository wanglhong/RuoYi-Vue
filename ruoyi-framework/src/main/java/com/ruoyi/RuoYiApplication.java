package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

/**
 * 若依框架启动类
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiApplication {

    /**
     * 启动
     *
     * @param mainApplicationClass 启动类
     * @param args                 参数
     */
    public static void run(Class<?> mainApplicationClass, String[] args) {
        SpringApplication.run(new Class<?>[]{mainApplicationClass, RuoYiApplication.class}, args);
    }

}
