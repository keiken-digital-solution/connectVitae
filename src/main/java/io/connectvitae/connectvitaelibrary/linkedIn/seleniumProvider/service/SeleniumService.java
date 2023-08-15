package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.service;

import io.connectvitae.connectvitaelibrary.linkedIn.config.LinkedInProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Duration;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

@Service
@RequiredArgsConstructor
public class SeleniumService {
    private final WebDriver driver;
    private final LinkedInProperties linkedInProperties;


//    @PostConstruct
//    void postConstruct(){
//        authenticate(
//                linkedInProperties.getAccounts().get(0).username(),
//                linkedInProperties.getAccounts().get(0).password());
//        getPositions("ahmedtaoufiq");
//        driver.quit();
//    }
    public void authenticate(String username, String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        int retries = 1;
        final int MAX_RETRIES = 5;


        driver.get("https://www.linkedin.com");

        // Refreshing until 5 times if the page is not loaded
        while (retries <= MAX_RETRIES) {

            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("session_key")))
                        .sendKeys(username);
                break;
            } catch (TimeoutException e) {
                driver.navigate().refresh();
                retries++;
            }
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("session_password")))
                .sendKeys(password);

        // Sleeping two seconds before clicking submit
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"main-content\"]/section[1]/div/div/form/div[2]/button"))
                .click();
    }
    public void getProfile(String userId){
        driver.get("https://www.linkedin.com/in/" + userId);
        WebElement experiences = driver.findElement(By.className("pvs-list"));
        System.out.println(experiences.getText());
    }

    public String getPositions(String userId){
        return get("experience",userId);
    }

    public String getEducations(String userId){
        return get("education",userId);
    }

    public String getSkills(String userId){
        return get("skills",userId);
    }

    public String getCertifications(String userId){
        return get("certifications",userId);
    }

    public String getLanguages(String userId){
        return get("languages",userId);
    }

    public String get(String informationType, String userId){
        driver.get("https://www.linkedin.com/in/" + userId + "/details/" + informationType);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("pvs-list")));
        return driver
                .findElement(By.className("pvs-list"))
                .getAttribute("innerHTML");
    }
}
