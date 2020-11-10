package org.example.rocketmq.interfaces;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author cpz
 * @Date 2020/11/7
 * @Description:
 */
public interface MySkin {

    String PERIODLOSER = "duobao_period_loser";

    String PERIODWINNER = "duobao_period_winner";

    @Input("duobao_period_loser")
    SubscribableChannel periodLoser();

    @Input("duobao_period_winner")
    SubscribableChannel periodWinner();

}
