package com.devsu.api.personacliente.service.RabbitMQ;

import com.devsu.api.personacliente.excepcion.Error;
import com.devsu.api.personacliente.model.dto.ClientDto;
import com.devsu.api.personacliente.model.entity.Client;
import com.devsu.api.personacliente.repository.ClientRepository;
import com.devsu.api.personacliente.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ClienteRequestConsumerService {

    @Autowired
    private ClientService clientService;

    private ClienteResponseProducerService clienteResponseService;


    @RabbitListener(queues = "${spring.rabbitmq.request.queue}")
    public void buscarCliente(String clientId) {
        ClientDto clienteDb= clientService.retrieveClient(clientId) ;

        clienteResponseService.responseCliente(clienteDb);

        log.info(String.format("Identifiacion: %s recibida", clientId));
        log.info("Cliente: {}", clienteDb);
    }
}

