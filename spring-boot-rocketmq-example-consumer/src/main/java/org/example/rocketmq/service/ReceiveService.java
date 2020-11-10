package org.example.rocketmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.example.rocketmq.interfaces.MySkin;
import org.example.rocketmq.pojo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Service;

/**
 * @Author cpz
 * @Date 2020/11/7
 * @Description: 消息接收
 */
@Service
public class ReceiveService {

    private final static Logger logger = LoggerFactory.getLogger(ReceiveService.class);

    /**
     * StreamListener(target = "input", condition = "headers['type'] == 'program_period_388'")
     * condition 限制某个属性
     * <p>
     * 消息均分，且只被消费一次
     * <p>
     * <p>
     * 1、不是有序的！！！
     * 2、处理失败就不处理了！！！
     *
     * @param student
     * @throws JsonProcessingException
     * @Payload 声明反序列化为对象 不加也没事
     */
    @StreamListener(MySkin.PERIODLOSER)
    private void loser(@Payload Student student) {
        logger.info("[loser消息处理][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), student.toString());
//        throw new RuntimeException("故意抛出的一个异常...");
    }

    @StreamListener(MySkin.PERIODWINNER)
    private void winner(@Payload Student student) {
        logger.info("[winner消息处理][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), student.toString());
//        throw new RuntimeException("故意抛出的一个异常...");
    }

    /**
     * 局部消息处理：<destination>.<group>.errors
     *
     * @param errorMessage

    @ServiceActivator(inputChannel = "period.errors")
    private void handleError(ErrorMessage errorMessage) {
        logger.info("[局部异常处理][消息内容：{}]", ExceptionUtils.getRootCauseMessage(errorMessage.getPayload()));
        logger.info("[局部异常处理][原消息内容：{}]", errorMessage.getOriginalMessage());
        logger.info("[局部异常处理][请求头内容：{}]", errorMessage.getHeaders());
    }
     */

    /**
     * 全局消息处理
     *
     * @param errorMessage

    @StreamListener(IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME)
    private void globalHandleError(ErrorMessage errorMessage) {
        logger.error("[全局异常处理][消息内容：{}]", ExceptionUtils.getRootCauseMessage(errorMessage.getPayload()));
        logger.error("[全局异常处理][原消息内容：{}]", errorMessage.getOriginalMessage());
        logger.error("[全局异常处理][请求头内容：{}]", errorMessage.getHeaders());
    }
     */

}
