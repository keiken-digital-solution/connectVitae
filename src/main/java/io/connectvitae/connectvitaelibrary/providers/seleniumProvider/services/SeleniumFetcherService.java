package io.connectvitae.connectvitaelibrary.providers.seleniumProvider.services;

import io.connectvitae.connectvitaelibrary.services.FetcherServiceInterface;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@SuppressWarnings("checkstyle:MemberName")
public class SeleniumFetcherService implements FetcherServiceInterface {
  private final WebDriver driver;
  private static final int MAX_WAIT_SECONDS = 4;
  private static final int WAIT_BEFORE_SUBMIT_CLICK = 2000;
  private static final int REFRESH_MAX_RETRIES = 5;
  public void authenticate(String username, String password) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT_SECONDS));
    int retries = 1;
    driver.get("https://www.linkedin.com");
    // Refreshing until REFRESH_MAX_RETRIES times if the page is not loaded
    while (retries <= REFRESH_MAX_RETRIES) {

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
      Thread.sleep(WAIT_BEFORE_SUBMIT_CLICK);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    driver.findElement(By.xpath("//*[@id=\"main-content\"]/section[1]/div/div/form/div[2]/button"))
        .click();
  }

  public String fetchUser(String profileId) {
    driver.get("https://www.linkedin.com/in/" + profileId);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT_SECONDS));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".pv-top-card h1")));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".pvs-loader__profile-card")));
    return driver.getPageSource();
  }

  // TODO: test the case where this is a group
  public String fetchExperiences(String profileId) {
    return fetch("experience", profileId);
  }

  public String fetchEducations(String profileId) {
    return fetch("education", profileId);
  }


  public String fetchSkills(String profileId) {
    return fetch("skills", profileId);
  }

  public String fetchCertifications(String profileId) {
    return fetch("certifications", profileId);
  }

  public String fetchLanguages(String profileId) {
    return fetch("languages", profileId);
  }

  private String fetch(String informationType, String profileId) {
    driver.get("https://www.linkedin.com/in/" + profileId + "/details/" + informationType);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT_SECONDS));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("pvs-list")));
    return driver
        .findElement(By.className("pvs-list"))
        .getAttribute("innerHTML");
  }
}
