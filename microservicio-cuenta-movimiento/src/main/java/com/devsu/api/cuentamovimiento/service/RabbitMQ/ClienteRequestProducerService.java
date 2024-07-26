package com.devsu.api.cuentamovimiento.service.RabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClienteRequestProducerService {
    @Value("${spring.rabbitmq.request.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.request.routingKey}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    public ClienteRequestProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void obtenerClientePorIdentificacion(String identificacion) {
        log.info("Proceso solicitud cliente");
        rabbitTemplate.convertAndSend(exchange, routingKey, identificacion);
        log.info(String.format("Mensage enviado %s", identificacion));
    }
}

