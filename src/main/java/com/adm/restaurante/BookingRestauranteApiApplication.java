package com.adm.restaurante;

import com.adm.restaurante.exceptions.BookingExceptions;
import com.adm.restaurante.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BookingRestauranteApiApplication {

	@Autowired
	EmailServiceImpl senderEmailService;

	public static void main(String[] args) {
		SpringApplication.run(BookingRestauranteApiApplication.class, args);
	}

}
