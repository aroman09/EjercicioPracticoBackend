package com.devsu.api.cuentamovimiento.service.RabbitMQ;

import com.devsu.api.cuentamovimiento.model.dto.ClientDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AccountConsumer {
    @RabbitListener(queues = "cliente-queue")
    public void receiveMessage(ClientDto clienteEvent) {
        // Procesa el mensaje recibido
        // Ejemplo: Crear una cuenta basada en el evento del cliente
        System.out.println("Received message: " + clienteEvent);
    }
}
