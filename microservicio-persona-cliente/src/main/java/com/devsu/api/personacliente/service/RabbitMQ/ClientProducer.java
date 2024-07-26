package com.devsu.api.personacliente.service.RabbitMQ;

import com.devsu.api.personacliente.model.dto.ClientDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final String EXCHANGE = "cliente-events-exchange";
    private static final String ROUTING_KEY = "cliente-routing-key";

    public void sendMessage(ClientDto clienteEvent) {
        amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, clienteEvent);
    }
}
