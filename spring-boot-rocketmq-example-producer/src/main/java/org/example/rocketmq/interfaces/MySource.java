package org.example.rocketmq.interfaces;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Author cpz
 * @Date 2020/11/7
 * @Description:
 */
public interface MySource {

    @Output("duobao_period_loser")
    MessageChannel periodLoser();

    @Output("duobao_period_winner")
    MessageChannel periodWinner();

}
