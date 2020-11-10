package org.example.rocketmq.controller;

import org.apache.rocketmq.common.message.MessageConst;
import org.example.rocketmq.interfaces.MySource;
import org.example.rocketmq.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cpz
 * @Date 2020/11/7
 * @Description:
 */
@RestController
@RequestMapping("/rocketmq/producer")
public class RocketMqController {

    @Autowired
    private MySource source;

    @GetMapping("/send")
    private String send(int keys) {

        int begin = 1;

        for (int i = 0; i < keys; i++) {
            Student student = new Student(begin + "" + i, "米狗蛋" + i, 20);

            Message<Student> message = MessageBuilder.withPayload(student)
                    .setHeader(MessageConst.PROPERTY_TAGS, "studentTags" + begin + "" + i)
                    .setHeader(MessageConst.PROPERTY_KEYS, "studentKeys" + begin + "" + i)
                    .build();

            source.periodLoser().send(message);
            source.periodWinner().send(message);

            System.out.println(message.toString());
        }

        begin += 1;

        return "发送消息成功";
    }

}
