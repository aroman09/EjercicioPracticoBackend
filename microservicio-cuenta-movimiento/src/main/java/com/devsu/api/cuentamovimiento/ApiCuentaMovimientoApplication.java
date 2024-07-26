package com.devsu.api.cuentamovimiento;


import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiCuentaMovimientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCuentaMovimientoApplication.class, args);
	}

	@Bean
	public MessageConverter converter(){
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
