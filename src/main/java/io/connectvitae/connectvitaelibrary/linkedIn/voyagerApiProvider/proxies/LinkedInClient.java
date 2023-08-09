package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.proxies;


import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.LinkedInAuthenticationDTO;
import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.LinkedInProfile;
import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.views.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "linkedInClient", url = "https://www.linkedin.com")
@Service
public interface LinkedInClient {
  @PostMapping(
      value = "/uas/authenticate",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  ResponseEntity<String> authenticate(
      @RequestHeader("x-li-user-agent") String xLiUserAgent,
      @RequestBody LinkedInAuthenticationDTO requestBody
  );

  @GetMapping("/voyager/api/identity/profiles/{profileId}/profileView")
  ProfileView fetchProfileView(
      @RequestHeader("cookie") String cookieValue,
      @RequestHeader("csrf-token") String jSessionId,
      @PathVariable("profileId") String profileId
  );


  @GetMapping("/voyager/api/identity/profiles/{profileId}/positions?count=100")
  PositionView fetchPositions(
      @RequestHeader("cookie") String cookieValue,
      @RequestHeader("csrf-token") String jSessionId,
      @PathVariable("profileId") String profileId
  );

  @GetMapping("/voyager/api/identity/profiles/{profileId}/skills?count=100")
  SkillView fetchSkills(
      @RequestHeader("cookie") String cookieValue,
      @RequestHeader("csrf-token") String jSessionId,
      @PathVariable("profileId") String profileId
  );

  @GetMapping("/voyager/api/identity/profiles/{profileId}/educations?count=100")
  EducationView fetchEducations(
      @RequestHeader("cookie") String cookieValue,
      @RequestHeader("csrf-token") String jSessionId,
      @PathVariable("profileId") String profileId
  );

  @GetMapping("/voyager/api/identity/profiles/{profileId}/certifications?count=100")
  CertificationView fetchCertifications(
      @RequestHeader("cookie") String cookieValue,
      @RequestHeader("csrf-token") String jSessionId,
      @PathVariable("profileId") String profileId
  );

  @GetMapping("/voyager/api/identity/profiles/{profileId}")
  LinkedInProfile fetchProfile(
      @RequestHeader("cookie") String cookieValue,
      @RequestHeader("csrf-token") String jSessionId,
      @PathVariable("profileId") String profileId
  );
}
