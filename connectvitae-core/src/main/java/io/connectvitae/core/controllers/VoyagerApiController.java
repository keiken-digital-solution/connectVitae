package io.connectvitae.core.controllers;


import io.connectvitae.contracts.models.Certification;
import io.connectvitae.contracts.models.Company;
import io.connectvitae.contracts.models.Education;
import io.connectvitae.contracts.models.Experience;
import io.connectvitae.contracts.models.GeneralProfile;
import io.connectvitae.contracts.models.School;
import io.connectvitae.contracts.models.Skill;
import io.connectvitae.contracts.models.User;
import io.connectvitae.core.config.LinkedInProperties;
import io.connectvitae.core.mappers.VoyagerApiMapperService;
import io.connectvitae.voyagerApiProvider.services.VoyagerApiExtractorService;
import io.connectvitae.voyagerApiProvider.services.VoyagerApiFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/linkedIn")
//TODO: Handle UnsupportedOperationException
public class VoyagerApiController {
  private final LinkedInProperties linkedInProperties;
  private final VoyagerApiFetcherService fetcherService;
  private final VoyagerApiExtractorService extractorService;
  private final VoyagerApiMapperService mapperService;

  private boolean isAuthenticated = false;
  @GetMapping("/{profileId}")
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

  @GetMapping("/company/{companyId}")
  public List<Company> getCompany(@PathVariable String companyId) {
    authenticate();
    return extractorService.getCompany(companyId)
        .getElements()
        .stream()
        .map(mapperService::mapCompany)
        .toList();
  }

  @GetMapping("/school/{schoolId}")
  public School getSchool(@PathVariable String schoolId) {
    authenticate();
    return mapperService.mapSchool(extractorService.getSchool(schoolId));
  }

  private void authenticate() {
    if (!isAuthenticated) {
      var accounts = linkedInProperties.getAccounts();
      fetcherService.authenticate(accounts.get(0).username(), accounts.get(0).password());
      isAuthenticated = true;
    }
  }
}
