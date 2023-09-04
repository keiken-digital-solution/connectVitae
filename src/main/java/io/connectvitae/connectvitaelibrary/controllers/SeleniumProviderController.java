package io.connectvitae.connectvitaelibrary.controllers;

import io.connectvitae.connectvitaelibrary.config.LinkedInProperties;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumCertification;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumEducation;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumExperience;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumProfile;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumSkill;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumUser;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.services.SeleniumExtractorService;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.services.SeleniumFetcherService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/selenium")
public class SeleniumProviderController {
  private final SeleniumExtractorService dataSeleniumExtractorService;
  private final LinkedInProperties linkedInProperties;
  private final SeleniumFetcherService seleniumFetcherService;
  private boolean isAuthenticated = false;


  @GetMapping("/profile/{profileId}")
  public SeleniumProfile getProfile(@PathVariable String profileId) {
    authenticate();
    return dataSeleniumExtractorService.getProfile(profileId);
  }

  @GetMapping("/profile/{profileId}/user")
  public SeleniumUser getUser(@PathVariable String profileId) {
    authenticate();
    return dataSeleniumExtractorService.getUser(profileId);
  }

  @GetMapping("/profile/{profileId}/experiences")
  public List<SeleniumExperience> getExperiences(@PathVariable String profileId) {
    authenticate();
    return dataSeleniumExtractorService.getExperiences(profileId);
  }

  @GetMapping("/profile/{profileId}/certifications")
  public List<SeleniumCertification> getCertifications(@PathVariable String profileId) {
    authenticate();
    return dataSeleniumExtractorService.getCertifications(profileId);
  }

  @GetMapping("/profile/{profileId}/educations")
  public List<SeleniumEducation> getEducations(@PathVariable String profileId) {
    authenticate();
    return dataSeleniumExtractorService.getEducations(profileId);
  }

  @GetMapping("/profile/{profileId}/skills")
  public List<SeleniumSkill> getSkills(@PathVariable String profileId) {
    authenticate();
    return dataSeleniumExtractorService.getSkills(profileId);
  }

  private void authenticate() {
    if (!isAuthenticated) {
      var accounts = linkedInProperties.getAccounts();
      seleniumFetcherService.authenticate(accounts.get(0).username(), accounts.get(0).password());
      isAuthenticated = true;
    }
  }
}
