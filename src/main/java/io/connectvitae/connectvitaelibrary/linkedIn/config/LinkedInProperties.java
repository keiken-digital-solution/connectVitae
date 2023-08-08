package io.connectvitae.connectvitaelibrary.linkedIn.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
