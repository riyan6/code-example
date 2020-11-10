package org.example.mq.quickstart;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @Author cpz
 * @Date 2020/10/11
 * @Description: 普通的消息消费者
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        // 1、创建 DefaultMQPushConsumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("demo_consumer_group");
        // 2、设置 Namesrv 地址
        consumer.setNamesrvAddr("47.113.201.110:9876");

        // - 设置消息拉取最大数
        consumer.setConsumeMessageBatchMaxSize(2);

        // 3、设置 subscribe，这里是要读取的主题信息；【指定要消费的主题，过滤规则】
        consumer.subscribe("Topic_Demo", "*");
        // 4、创建消息监听MessageListener
        consumer.setMessageListener(new MessageListenerConcurrently() {

            // 5、获取消息信息
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                // 迭代
                for (MessageExt item : list) {
                    try {
                        // 获取主题
                        String topic = item.getTopic();
                        // 获取标签
                        String tags = item.getTags();
                        // 获取信息
                        String body = new String(item.getBody(), RemotingHelper.DEFAULT_CHARSET);

                        System.out.println("CONSUMER消费消息：TOPIC:" + topic + " -> TAGS:" + tags + " -> BODY:" + body);
                    } catch (Exception e) {
                        System.out.println("获取消息失败！");
                        // 发送消息消费失败状态，进行重试消费
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                // 6、返回消息读取状态
                // 发送消息消费成功状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 开启 Consumer
        consumer.start();
    }

}
