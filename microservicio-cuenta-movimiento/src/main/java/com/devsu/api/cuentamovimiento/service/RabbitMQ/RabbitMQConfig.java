package com.devsu.api.cuentamovimiento.service.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String CLIENTE_REQUEST_QUEUE = "clienteRequestQueue";
    public static final String CLIENTE_RESPONSE_QUEUE = "clienteResponseQueue";

    @Bean
    public Queue clienteRequestQueue() {
        return new Queue(CLIENTE_REQUEST_QUEUE, false);
    }

    @Bean
    public Queue clienteResponseQueue() {
        return new Queue(CLIENTE_RESPONSE_QUEUE, false);
    }
}
