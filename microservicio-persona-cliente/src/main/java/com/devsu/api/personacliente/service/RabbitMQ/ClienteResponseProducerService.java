package com.devsu.api.personacliente.service.RabbitMQ;

import com.devsu.api.personacliente.manageObject.ClientMapper;
import com.devsu.api.personacliente.model.dto.ClientDto;
import com.devsu.api.personacliente.model.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClienteResponseProducerService {
    @Value("${spring.rabbitmq.response.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.response.routingKey}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ClientMapper clientMapper;

    public ClienteResponseProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void responseCliente(ClientDto clienteDto) {
        log.info(String.format("Cliente enviado %s", clienteDto));
        rabbitTemplate.convertAndSend(exchange, routingKey, clienteDto);
    }
}
