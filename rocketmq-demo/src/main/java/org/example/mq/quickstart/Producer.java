package org.example.mq.quickstart;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

/**
 * @Author cpz
 * @Date 2020/10/11
 * @Description: 普通的消息生产者
 */
public class Producer {

    /**
     * 主程序
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 1、创建 DefaultMQProducer
        DefaultMQProducer producer = new DefaultMQProducer("demo_producer_group");
        // 2、设置 NameSrv地址
        producer.setNamesrvAddr("47.113.201.110:9876");
        // 3、开启 DefaultMQProducer
        producer.start();
        // 4、创建消息；【主题；标签，主要用于消息过滤；消息的唯一值；消息内容】
        Message message = new Message(
                "Topic_Demo",
                "Tags",
                "Keys_1",
                "Hello RocketMQ".getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        // 5、发送消息
        SendResult sendResult = producer.send(message);
        System.out.println(sendResult);
        // 6、关闭 DefaultMQProducer
        producer.shutdown();
    }

}
