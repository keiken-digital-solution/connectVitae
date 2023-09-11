package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.proxies;

import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.VoyagerApiAuthenticationDTO;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.VoyagerApiProfile;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.VoyagerApiSchool;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.CertificationView;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.CompanyView;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.EducationView;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.PositionView;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.ProfileView;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.SkillView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "linkedInClient", url = "https://www.linkedin.com")
@Service
public interface LinkedInClient {
  @PostMapping(
      value = "/uas/authenticate",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  ResponseEntity<String> authenticate(
      @RequestHeader("x-li-user-agent") String xLiUserAgent,
      @RequestBody VoyagerApiAuthenticationDTO requestBody
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
  VoyagerApiProfile fetchProfile(
      @RequestHeader("cookie") String cookieValue,
      @RequestHeader("csrf-token") String jSessionId,
      @PathVariable("profileId") String profileId
  );

  @GetMapping("/voyager/api/organization/companies?decorationId=com.linkedin.voyager.deco.organization.web.WebFullCompanyMain-12&q=universalName")
  CompanyView fetchCompanyById(
      @RequestHeader("cookie") String cookieValue,
      @RequestHeader("csrf-token") String jSessionId,
      @RequestParam("universalName") String universalName
  );

  @GetMapping("/voyager/api/entities/schools/{schoolId}")
  VoyagerApiSchool fetchSchool(
      @RequestHeader("cookie") String cookieValue,
      @RequestHeader("csrf-token") String jSessionId,
      @PathVariable("schoolId") String schoolId
  );
}
