package com.devsu.api.cuentamovimiento.service.RabbitMQ;

import com.devsu.api.cuentamovimiento.model.dto.ClientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;

@Configuration
@Slf4j
public class ClienteResponseConsumerService {
    private CompletableFuture<ClientDto> clienteDTOCompletableFuture = new CompletableFuture<>();


    @RabbitListener(queues = "${spring.rabbitmq.response.queue}")
    public void recibirClienteDTO(ClientDto clienteDTO) {
        log.info(String.format("Cliente recibido"));
        log.info("Cliente: {}", clienteDTO);

        clienteDTOCompletableFuture.complete(clienteDTO);
        clienteDTOCompletableFuture = new CompletableFuture<>();
    }

    public CompletableFuture<ClientDto> obtenerClienteDTO() {
        return clienteDTOCompletableFuture;
    }

}
