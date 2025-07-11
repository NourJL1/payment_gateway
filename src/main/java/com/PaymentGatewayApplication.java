package com;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaymentGatewayApplication.class, args);
		System.out.println("todo bien");
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")));
	}

}
