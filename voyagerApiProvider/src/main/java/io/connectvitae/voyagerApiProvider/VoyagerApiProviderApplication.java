package io.connectvitae.voyagerApiProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VoyagerApiProviderApplication {
  public static void main(String[] args) {
    SpringApplication.run(VoyagerApiProviderApplication.class, args);
  }
}
