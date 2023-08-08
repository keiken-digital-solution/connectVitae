package io.connectvitae.connectvitaelibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ConnectVitaeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectVitaeApplication.class, args);
	}

}
