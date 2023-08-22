package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.config;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {
    @Bean
    public WebDriver driver(){
        final ChromeOptions chromeOptions = new ChromeOptions();
    //    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
//        chromeOptions.addArguments("--headless");

    //        //Adding proxy options if needed
    //        Proxy proxy = new Proxy();
    //        proxy.setHttpProxy("<HOST:PORT>");
    //        chromeOptions.setCapability("proxy", proxy);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        return driver;
    }
}
