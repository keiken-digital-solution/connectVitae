package io.connectvitae.seleniumProvider.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {
  @Value("${config.linkedin.selenium.web-driver-location}")
  private String webDriverLocation;

  @Bean
  public WebDriver driver() {
    System.setProperty("webdriver.chrome.driver", webDriverLocation);
    ChromeOptions chromeOptions = new ChromeOptions();
    //    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
    //    chromeOptions.addArguments("--headless");

    //        //Adding proxy options if neededgit
    //        Proxy proxy = new Proxy();
    //        proxy.setHttpProxy("<HOST:PORT>");
    //        chromeOptions.setCapability("proxy", proxy);
    ChromeDriver driver = new ChromeDriver(chromeOptions);
    return driver;
  }
}
