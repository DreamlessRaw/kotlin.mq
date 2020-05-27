package com.dreamless.mq

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.annotation.Resource
import kotlin.collections.HashMap

@RestController
class TestMqController {
    @Resource
    lateinit var rabbitTemplate: RabbitTemplate

    @PostMapping("send/{msg}")
    fun send(@PathVariable msg: String): String {
        val messageId = UUID.randomUUID().toString()
        val createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        val map: HashMap<String, Any> = hashMapOf()
        map["id"] = (0..100).random().toLong()
        map["messageId"] = messageId;
        map["messageData"] = msg;
        map["createTime"] = createTime;
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", msg);
        return "ok";
    }

}
