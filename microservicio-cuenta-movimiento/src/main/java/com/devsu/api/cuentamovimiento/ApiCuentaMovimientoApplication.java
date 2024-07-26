package com.devsu.api.cuentamovimiento;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiCuentaMovimientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCuentaMovimientoApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
