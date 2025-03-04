package com.devsu.api.personacliente.service.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClienteResponseProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public ClienteResponseProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendClienteResponse(String response) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.CLIENTE_RESPONSE_QUEUE, response);
        log.info("Respuesta enviada a la cola de respuestas: " + response);
    }
}
