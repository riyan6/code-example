package org.example.rocketmq;

import org.example.rocketmq.interfaces.MySkin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @Author cpz
 * @Date 2020/11/7
 * @Description: 消费者
 */
@SpringBootApplication
@EnableBinding(MySkin.class)
public class RocketMqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqConsumerApplication.class, args);
    }

}
