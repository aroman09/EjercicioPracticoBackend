package com.devsu.api.personacliente.service.RabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.devsu.api.personacliente.model.dto.ClientDto;
import com.devsu.api.personacliente.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ClienteRequestConsumerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ClientService clienteService; // Servicio para acceder a la base de datos de clientes

    @Autowired
    private ObjectMapper objectMapper;
    private ClienteResponseProducerService clienteResponseService;

    @RabbitListener(queues = RabbitMQConfig.CLIENTE_REQUEST_QUEUE)
    public void receiveClienteRequest(String clienteId) {
        System.out.println("Solicitud recibida para el cliente ID: " + clienteId);

        try {
            ClientDto cliente = clienteService.retrieveClient(clienteId); // Obtener el cliente desde la base de datos

            String clienteJson = objectMapper.writeValueAsString(cliente);
            clienteResponseService.sendClienteResponse(clienteJson);
  
            log.info("Cliente encontrado y enviado a la cola de respuesta: " + clienteJson);
        } catch (Exception e) {
            clienteResponseService.sendClienteResponse("");
            log.error("Error al procesar el cliente ID: " + e.getLocalizedMessage());
        }
    }
}

