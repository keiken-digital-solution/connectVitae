package io.connectvitae.connectvitaelibrary.controllers;

import io.connectvitae.connectvitaelibrary.config.LinkedInProperties;
import io.connectvitae.connectvitaelibrary.mappers.SeleniumMapperService;
import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Education;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.GeneralProfile;
import io.connectvitae.connectvitaelibrary.models.Language;
import io.connectvitae.connectvitaelibrary.models.Skill;
import io.connectvitae.connectvitaelibrary.models.User;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.services.SeleniumExtractorService;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.services.SeleniumFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/selenium")
public class SeleniumProviderController {
  private final SeleniumExtractorService extractorService;
  private final LinkedInProperties linkedInProperties;
  private final SeleniumFetcherService fetcherService;
  private final SeleniumMapperService mapperService;
  private boolean isAuthenticated = false;


  @GetMapping("/profile/{profileId}")
  public GeneralProfile getProfile(@PathVariable String profileId) {
    authenticate();
    return mapperService.mapGeneralProfile(extractorService.getProfile(profileId));
  }

  @GetMapping("/profile/{profileId}/user")
  public User getUser(@PathVariable String profileId) {
    authenticate();
    return mapperService.mapUser(extractorService.getUser(profileId));
  }

  @GetMapping("/profile/{profileId}/experiences")
  public List<Experience> getExperiences(@PathVariable String profileId) {
    authenticate();
    return extractorService.getExperiences(profileId)
        .stream()
        .map(mapperService::mapExperience)
        .collect(Collectors.toList());
  }

  @GetMapping("/profile/{profileId}/certifications")
  public List<Certification> getCertifications(@PathVariable String profileId) {
    authenticate();
    return extractorService.getCertifications(profileId)
        .stream()
        .map(mapperService::mapCertification)
        .collect(Collectors.toList());
  }

  @GetMapping("/profile/{profileId}/educations")
  public List<Education> getEducations(@PathVariable String profileId) {
    authenticate();
    return extractorService.getEducations(profileId)
        .stream()
        .map(mapperService::mapEducation)
        .collect(Collectors.toList());
  }

  @GetMapping("/profile/{profileId}/skills")
  public List<Skill> getSkills(@PathVariable String profileId) {
    authenticate();
    return extractorService.getSkills(profileId)
        .stream()
        .map(mapperService::mapSkill)
        .collect(Collectors.toList());
  }

  @GetMapping("/profile/{profileId}/languages")
  public List<Language> getLanguages(@PathVariable String profileId) {
    authenticate();
    return extractorService.getLanguages(profileId)
        .stream()
        .map(mapperService::mapLanguage)
        .collect(Collectors.toList());
  }

  private void authenticate() {
    if (!isAuthenticated) {
      var accounts = linkedInProperties.getAccounts();
      fetcherService.authenticate(accounts.get(0).username(), accounts.get(0).password());
      isAuthenticated = true;
    }
  }
}
