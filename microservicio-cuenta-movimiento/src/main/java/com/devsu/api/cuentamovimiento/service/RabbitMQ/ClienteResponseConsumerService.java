package com.devsu.api.cuentamovimiento.service.RabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class ClienteResponseConsumerService {
    private CompletableFuture<String> clienteStrCompletableFuture = new CompletableFuture<>();

    @RabbitListener(queues = RabbitMQConfig.CLIENTE_RESPONSE_QUEUE)
    public void recibirClienteDTO(String client) {
        log.info("Cliente recibido {} ",client);

        clienteStrCompletableFuture.complete(client);
        clienteStrCompletableFuture = new CompletableFuture<>();
    }


   public CompletableFuture<String> obtenerClienteStr() {
        return clienteStrCompletableFuture;
    }
}
