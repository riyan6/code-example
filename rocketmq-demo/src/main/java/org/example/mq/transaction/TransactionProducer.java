package org.example.mq.transaction;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Author cpz
 * @Date 2020/10/11
 * @Description: 顺序的消息生产者
 */
public class TransactionProducer {

    /**
     * 主程序
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 1、创建 DefaultMQProducer
        TransactionMQProducer producer = new TransactionMQProducer("demo_producer_transaction_group");

        // 2、设置 NameSrv地址
        producer.setNamesrvAddr("47.113.201.110:9876");

        // 指定消息监听对象，用于执行本地事务，消息回查
        TransactionListener transactionListener = new TransactionListenerImpl();
        producer.setTransactionListener(transactionListener);

        // 线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("client-transaction-msg-check");
                        return null;
                    }
                }
        );


        // 3、开启 DefaultMQProducer
        producer.start();

        // 4、创建消息；【主题；标签，主要用于消息过滤；消息的唯一值；消息内容】
        Message message = new Message(
                "Topic_Order_Demo",
                "Tags",
                "Keys_",
                ("Hello TransactionRocketMQ").getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        // 5、发送事务消息
        TransactionSendResult result = producer.sendMessageInTransaction(message, "Hello Transaction");

        System.out.println(result);

        // 6、关闭 DefaultMQProducer
        producer.shutdown();
    }

}
