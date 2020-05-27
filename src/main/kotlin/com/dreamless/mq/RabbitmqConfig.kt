package com.dreamless.mq

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitmqConfig {

    /**
     * 队列名称
     */
    @Bean
    fun testQueue(): Queue {
        return Queue("TestQueue")
    }

    /**
     * 交换机名称
     */
    @Bean
    fun testDirectExchange(): DirectExchange {
        return DirectExchange("TestDirectExchange")
    }

    /**
     * 将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
     */
    @Bean
    fun bindingDirect(): Binding {
        return BindingBuilder.bind(testQueue()).to(testDirectExchange()).with("TestDirectRouting")
    }

    @Bean
    fun lonelyDirectExchange(): DirectExchange? {
        return DirectExchange("lonelyDirectExchange")
    }


}
