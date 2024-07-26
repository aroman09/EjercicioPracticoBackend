package com.devsu.api.personacliente.service.RabbitMQ;

import com.devsu.api.personacliente.excepcion.Error;
import com.devsu.api.personacliente.model.entity.Client;
import com.devsu.api.personacliente.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ClienteRequestConsumerService {
    private ClientRepository clienteRepository;
    private ClienteResponseProducerService clienteResponseService;


    @RabbitListener(queues = "${spring.rabbitmq.request.queue}")
    public void buscarCliente(String identificacion) {
        Client clienteDb= clienteRepository.findById(identificacion) .orElseThrow(
                ()-> new Error("996")
        );
        //enviar a la cola cliente response

        clienteResponseService.responseCliente(clienteDb);

        log.info(String.format("Identifiacion: %s recibida", identificacion));
        log.info("Cliente: {}", clienteDb);
    }
}

