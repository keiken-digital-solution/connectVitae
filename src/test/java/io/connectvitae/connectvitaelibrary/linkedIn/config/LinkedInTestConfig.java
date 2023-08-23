package io.connectvitae.connectvitaelibrary.linkedIn.config;

import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class LinkedInTestConfig {
    @Bean
    public WebDriver driver() {
        return Mockito.mock(WebDriver.class);
    }
}
