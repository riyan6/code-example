package org.example.mq.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @Author cpz
 * @Date 2020/10/11
 * @Description: 顺序的消息生产者
 */
public class OrderProducer {

    /**
     * 主程序
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 1、创建 DefaultMQProducer
        DefaultMQProducer producer = new DefaultMQProducer("demo_producer_order_group");
        // 2、设置 NameSrv地址
        producer.setNamesrvAddr("47.113.201.110:9876");
        // 3、开启 DefaultMQProducer
        producer.start();


        for (int i = 0; i < 5; i++) {
            // 4、创建消息；【主题；标签，主要用于消息过滤；消息的唯一值；消息内容】
            Message message = new Message(
                    "Topic_Order_Demo",
                    "Tags",
                    "Keys_" + i,
                    ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // 5、发送消息
            SendResult sendResult = producer.send(message,
                    new MessageQueueSelector() {
                        /**
                         *
                         * @param mqs 队列个数
                         * @param message
                         * @param args
                         * @return
                         */
                        @Override
                        public MessageQueue select(List<MessageQueue> mqs, Message message, Object args) {
                            // 获取队列下标
                            int index = (int) args;
                            return mqs.get(index);
                        }
                    }, 1);

            System.out.println(sendResult);
        }


        // 6、关闭 DefaultMQProducer
        producer.shutdown();
    }

}
