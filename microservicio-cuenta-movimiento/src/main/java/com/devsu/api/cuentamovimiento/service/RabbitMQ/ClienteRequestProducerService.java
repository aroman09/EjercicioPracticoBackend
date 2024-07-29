package com.devsu.api.cuentamovimiento.service.RabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@Slf4j
public class ClienteRequestProducerService {

    private RabbitTemplate rabbitTemplate;

    public ClienteRequestProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void obtenerClientePorIdentificacion(String identificacion) {
        log.info(String.format("Mensage enviado %s", identificacion));
        rabbitTemplate.convertAndSend(RabbitMQConfig.CLIENTE_REQUEST_QUEUE, identificacion);
        log.info(String.format("Mensage enviado %s", identificacion));
    }
}

