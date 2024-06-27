package io.connectvitae.core.controllers;

import io.connectvitae.contracts.models.*;
import io.connectvitae.core.config.LinkedInProperties;
import io.connectvitae.core.mappers.VoyagerApiMapperService;
import io.connectvitae.voyagerApiProvider.services.VoyagerApiExtractorService;
import io.connectvitae.voyagerApiProvider.services.VoyagerApiFetcherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/linkedIn")
public class VoyagerApiController {
  private final LinkedInProperties linkedInProperties;
  private final VoyagerApiFetcherService fetcherService;
  private final VoyagerApiExtractorService extractorService;
  private final VoyagerApiMapperService mapperService;
  private boolean isAuthenticated = false;

  @Operation(summary = "Get general profile by Linkedin ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "The profile is found"),
          @ApiResponse(responseCode = "404", description = "Profile not found")
  })
  @GetMapping("/{profileId}")
  public GeneralProfile getProfile(@PathVariable String profileId) {
    authenticate();
    return mapperService.mapGeneralProfile(extractorService.getProfile(profileId));
  }

  @Operation(summary = "Get user details by LinkedIn ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "The user details are found"),
          @ApiResponse(responseCode = "404", description = "User details not found")
  })
  @GetMapping("/profile/{profileId}/user")
  public User getUser(@PathVariable String profileId) {
    authenticate();
    return mapperService.mapUser(extractorService.getUser(profileId));
  }

  @Operation(summary = "Get experiences by LinkedIn ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Experiences are found "),
          @ApiResponse(responseCode = "404", description = "Experiences not found")
  })
  @GetMapping("/profile/{profileId}/experiences")
  public List<Experience> getExperiences(@PathVariable String profileId) {
    authenticate();
    return extractorService.getExperiences(profileId)
            .stream()
            .map(mapperService::mapExperience)
            .collect(Collectors.toList());
  }

  @Operation(summary = "Get certifications by LinkedIn ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Certifications are found"),
          @ApiResponse(responseCode = "404", description = "Certifications not found")
  })
  @GetMapping("/profile/{profileId}/certifications")
  public List<Certification> getCertifications(@PathVariable String profileId) {
    authenticate();
    return extractorService.getCertifications(profileId)
            .stream()
            .map(mapperService::mapCertification)
            .collect(Collectors.toList());
  }

  @Operation(summary = "Get educations by LinkedIn ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Educations are Found"),
          @ApiResponse(responseCode = "404", description = "Educations not found")
  })
  @GetMapping("/profile/{profileId}/educations")
  public List<Education> getEducations(@PathVariable String profileId) {
    authenticate();
    return extractorService.getEducations(profileId)
            .stream()
            .map(mapperService::mapEducation)
            .collect(Collectors.toList());
  }

  @Operation(summary = "Get skills by LinkedIn ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Skills are found"),
          @ApiResponse(responseCode = "404", description = "Skills not found")
  })
  @GetMapping("/profile/{profileId}/skills")
  public List<Skill> getSkills(@PathVariable String profileId) {
    authenticate();
    return extractorService.getSkills(profileId)
            .stream()
            .map(mapperService::mapSkill)
            .collect(Collectors.toList());
  }

  @Operation(summary = "Get company details by company ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Company details are found"),
          @ApiResponse(responseCode = "404", description = "Company details not found")
  })
  @GetMapping("/company/{companyId}")
  public List<Company> getCompany(@PathVariable String companyId) {
    authenticate();
    return extractorService.getCompany(companyId)
            .getElements()
            .stream()
            .map(mapperService::mapCompany)
            .collect(Collectors.toList());
  }

  @Operation(summary = "Get school details by school ID")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "School details are found"),
          @ApiResponse(responseCode = "404", description = "School details not found")
  })
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