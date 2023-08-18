package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services;

import io.connectvitae.connectvitaelibrary.linkedIn.config.LinkedInProperties;
import io.connectvitae.connectvitaelibrary.models.*;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeleniumService {
    private final WebDriver driver;
    private final ScrapeService scrapeService;
    private final LinkedInProperties linkedInProperties;

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

    public Profile getProfile (String profileId) {
        return Profile.builder()
                .experiences(getExperiences(profileId))
                .educations(getEducations(profileId))
                .skills(getSkills(profileId))
                .certifications(getCertifications(profileId))
                .user(getUser(profileId))
                .build();
    }
    public User getUser(String profileId){
        driver.get("https://www.linkedin.com/in/" + profileId);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.text-heading-xlarge.inline.t-24.v-align-middle.break-words")));
        return scrapeService.scrapeUser(driver.getPageSource());
    }

    // TODO: test the case where this is a group
    public List<Experience> getExperiences(String profileId) {
        var experiences =  get("experience",profileId);
        List<Experience> experienceList = new ArrayList<>();
        scrapeService.getElements(experiences).forEach(element -> {
            experienceList.add(scrapeService.scrapeExperience(element));
        });

        return experienceList;
    }

    public List<Education> getEducations(String profileId) {
        var educations =  get("education",profileId);
        List<Education> educationList = new ArrayList<>();
        scrapeService.getElements(educations).forEach(element -> {
            educationList.add(scrapeService.scrapeEducation(element));
        });

        return educationList;
    }

    public List<Skill> getSkills(String profileId) {
        var skills = get("skills",profileId);
        List<Skill> skillList = new ArrayList<>();
        scrapeService.getElements(skills).forEach(element -> {
            skillList.add(scrapeService.scrapeSkill(element));
        });
        return skillList;
    }

    public List<Certification> getCertifications(String profileId) {
        var certifications = get("certifications",profileId);
        List<Certification> certificationList = new ArrayList<>();
        scrapeService.getElements(certifications).forEach(element -> {
            certificationList.add(scrapeService.scrapeCertification(element));
        });

        return certificationList;
    }

    public String getLanguages(String profileId) {
        return get("languages",profileId);
    }

    private String get(String informationType, String profileId) {
        driver.get("https://www.linkedin.com/in/" + profileId + "/details/" + informationType);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("pvs-list")));
        return driver
                .findElement(By.className("pvs-list"))
                .getAttribute("innerHTML");
    }
}
