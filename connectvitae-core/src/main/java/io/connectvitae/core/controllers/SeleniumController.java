package io.connectvitae.core.controllers;

import io.connectvitae.contracts.models.*;
import io.connectvitae.core.config.LinkedInProperties;
import io.connectvitae.core.mappers.SeleniumMapperService;
import io.connectvitae.seleniumProvider.services.SeleniumExtractorService;
import io.connectvitae.seleniumProvider.services.SeleniumFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/selenium")
public class SeleniumController {
  private final SeleniumExtractorService extractorService;
  private final LinkedInProperties linkedInProperties;
  private final SeleniumFetcherService fetcherService;
  private final SeleniumMapperService mapperService;
  private boolean isAuthenticated = false;

  @Operation(summary = "Get profile information")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved profile"),
          @ApiResponse(responseCode = "404", description = "Profile not found"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/profile/{profileId}")
  public GeneralProfile getProfile(@PathVariable String profileId) {
    authenticate();
    return mapperService.mapGeneralProfile(extractorService.getProfile(profileId));
  }

  @Operation(summary = "Get user information")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
          @ApiResponse(responseCode = "404", description = "User not found"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/profile/{profileId}/user")
  public User getUser(@PathVariable String profileId) {
    authenticate();
    return mapperService.mapUser(extractorService.getUser(profileId));
  }

  @Operation(summary = "Get experiences")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved experiences"),
          @ApiResponse(responseCode = "404", description = "Experiences not found"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/profile/{profileId}/experiences")
  public List<Experience> getExperiences(@PathVariable String profileId) {
    authenticate();
    return extractorService.getExperiences(profileId)
            .stream()
            .map(mapperService::mapExperience)
            .collect(Collectors.toList());
  }

  @Operation(summary = "Get certifications")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved certifications"),
          @ApiResponse(responseCode = "404", description = "Certifications not found"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/profile/{profileId}/certifications")
  public List<Certification> getCertifications(@PathVariable String profileId) {
    authenticate();
    return extractorService.getCertifications(profileId)
            .stream()
            .map(mapperService::mapCertification)
            .collect(Collectors.toList());
  }

  @Operation(summary = "Get educations")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved educations"),
          @ApiResponse(responseCode = "404", description = "Educations not found"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/profile/{profileId}/educations")
  public List<Education> getEducations(@PathVariable String profileId) {
    authenticate();
    return extractorService.getEducations(profileId)
            .stream()
            .map(mapperService::mapEducation)
            .collect(Collectors.toList());
  }

  @Operation(summary = "Get skills")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved skills"),
          @ApiResponse(responseCode = "404", description = "Skills not found"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/profile/{profileId}/skills")
  public List<Skill> getSkills(@PathVariable String profileId) {
    authenticate();
    return extractorService.getSkills(profileId)
            .stream()
            .map(mapperService::mapSkill)
            .collect(Collectors.toList());
  }

  @Operation(summary = "Get languages")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved languages"),
          @ApiResponse(responseCode = "404", description = "Languages not found"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
  })
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