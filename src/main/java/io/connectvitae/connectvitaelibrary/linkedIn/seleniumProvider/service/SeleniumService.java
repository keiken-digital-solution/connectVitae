package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.service;

import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.config.LinkedInProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

@Service
@RequiredArgsConstructor
public class SeleniumService {
    private final WebDriver driver;
    private final LinkedInProperties linkedInProperties;
    long initialTime;
    long loginTime;
    long firstRequestTime;
    @PostConstruct
    void postConstruct(){
        authenticate(
                linkedInProperties.getAccounts().get(0).username(),
                linkedInProperties.getAccounts().get(0).password());
        getPageByUserId("ahmedtaoufiq");
        driver.quit();
    }
    public void authenticate(String username, String password){
        initialTime = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

        driver. get("chrome://settings/clearBrowserData");
        driver.get("https://www.linkedin.com");
//
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("session_key")))
                .sendKeys(username);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("session_password")))
                .sendKeys(password);

        driver.findElement(By.xpath("//*[@id=\"main-content\"]/section[1]/div/div/form/div[2]/button"))
                .click();

        loginTime = System.currentTimeMillis();
        System.out.println("================>"+(loginTime - initialTime));
    }
    public void getPageByUserId(String userId){
        driver.get("https://www.linkedin.com/in/"+userId);
        WebElement experiences = driver.findElement(By.className("pvs-list"));
        System.out.println(experiences.getText());
        firstRequestTime = System.currentTimeMillis();
        System.out.println("================>"+(firstRequestTime-loginTime));
    }
}
