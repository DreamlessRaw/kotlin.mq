package com.dreamless.mq

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = ["TestQueue"])
class DirectReceiver {

    @RabbitHandler
    fun process(message: Message, channel: Channel, body: String) {
        print("接收结果 $body")
        channel.basicAck(message.messageProperties.deliveryTag, false)
    }

}
