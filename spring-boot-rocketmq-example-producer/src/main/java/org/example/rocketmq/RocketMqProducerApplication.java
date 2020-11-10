package org.example.rocketmq;

import org.example.rocketmq.interfaces.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @Author cpz
 * @Date 2020/11/7
 * @Description: 消费者
 */
@SpringBootApplication
@EnableBinding(MySource.class)
public class RocketMqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqProducerApplication.class, args);
    }

}
