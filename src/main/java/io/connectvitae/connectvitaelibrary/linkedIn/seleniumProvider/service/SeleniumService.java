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
        getPositions("ahmedtaoufiq");
        driver.quit();
    }
    public void authenticate(String username, String password){
        int retries = 1;
        int maxRetries = 5;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        driver. get("chrome://settings/clearBrowserData");

        initialTime = System.currentTimeMillis();
        driver.get("https://www.linkedin.com");
        // Refreshing until 5 times if the page is not loaded
        while (retries <= maxRetries) {
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
            Thread.sleep(2000); // Sleep for 3000 milliseconds (3 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"main-content\"]/section[1]/div/div/form/div[2]/button"))
                .click();

        loginTime = System.currentTimeMillis();
        System.out.println("================>"+(loginTime - initialTime));
    }
    public void getProfile(String userId){
        driver.get("https://www.linkedin.com/in/" + userId);

        WebElement experiences = driver.findElement(By.className("pvs-list"));
        System.out.println(experiences.getText());
        firstRequestTime = System.currentTimeMillis();
        System.out.println("================>"+(firstRequestTime-loginTime));
    }

    public void getPositions(String userId){
        get("experience",userId);
    }

    public void getEducations(String userId){
        get("education",userId);
    }

    public void getSkills(String userId){
        get("skills",userId);
    }

    public void getCertifications(String userId){
        get("certifications",userId);
    }

    public void getLanguages(String userId){
        get("languages",userId);
    }

    public void get(String informationType, String userId){
        driver.get("https://www.linkedin.com/in/" + userId + "/details/" + informationType);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("pvs-list")));
        WebElement experiences = driver.findElement(By.className("pvs-list"));
        System.out.println(experiences.getAttribute("innerHTML"));
        firstRequestTime = System.currentTimeMillis();
        System.out.println("================>"+(firstRequestTime-loginTime));
    }
}
