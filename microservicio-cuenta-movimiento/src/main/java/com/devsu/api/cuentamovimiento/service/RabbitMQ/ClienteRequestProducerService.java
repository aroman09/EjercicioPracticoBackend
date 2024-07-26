package com.devsu.api.cuentamovimiento.service.RabbitMQ;

import com.devsu.api.personacliente.model.entity.Client;
import com.devsu.api.personacliente.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
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
        log.info(String.format("Mensage enviado %s", identificacion));
        rabbitTemplate.convertAndSend(exchange, routingKey, identificacion);
        log.info(String.format("Mensage enviado %s", identificacion));
    }
}

