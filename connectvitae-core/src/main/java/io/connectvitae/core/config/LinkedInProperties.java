package io.connectvitae.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "config.linkedin")
@Component
public class LinkedInProperties {
  private List<Account> accounts;

  public record Account(
      String username,
      String password
  ) {
  }
}
