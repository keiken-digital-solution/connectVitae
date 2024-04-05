package io.connectvitae.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"io.connectvitae"})
public class ConnectVitaeApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConnectVitaeApplication.class, args);
  }

}
