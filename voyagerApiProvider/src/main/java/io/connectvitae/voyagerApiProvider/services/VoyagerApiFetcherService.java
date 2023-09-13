package io.connectvitae.voyagerApiProvider.services;



import io.connectvitae.contracts.services.FetcherServiceInterface;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiAuthenticationDTO;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiProfile;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiSchool;
import io.connectvitae.voyagerApiProvider.models.views.CertificationView;
import io.connectvitae.voyagerApiProvider.models.views.CompanyView;
import io.connectvitae.voyagerApiProvider.models.views.EducationView;
import io.connectvitae.voyagerApiProvider.models.views.PositionView;
import io.connectvitae.voyagerApiProvider.models.views.ProfileView;
import io.connectvitae.voyagerApiProvider.models.views.SkillView;
import io.connectvitae.voyagerApiProvider.proxies.VoyagerApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class VoyagerApiFetcherService implements FetcherServiceInterface {
  private final VoyagerApiClient voyagerApiClient;
  // TODO: find a better way to handle this
  private Map<String, String> storedCookies;
  /**
   * Gets the credentials and does an authentication request to the LinkedIn website, then stores
   * the cookies that will be used for future requests.
   *
   * @param username,password The credential for LinkedIn account with which the requests will be done.
   */
  public void authenticate(String username, String password) {
    ResponseEntity<String> authenticationResponse =
        voyagerApiClient.authenticate(
            "LIAuthLibrary:3.2.4 com.linkedin.LinkedIn:8.8.1 iPhone:8.3",
            VoyagerApiAuthenticationDTO.builder()
                .sessionKey(username)
                .sessionPassword(password)
                .build()
        );

    storedCookies = getCookies(authenticationResponse);

    // Logging the response body
    System.out.println(authenticationResponse.getBody());
  }
  /**
   * Makes required requests for different information, to fetch a complete voyager api profile view object
   *
   * @param profileId The id of the user of which we want to retrieve information.
   * @return The voyager api profile view object.
   */
  public ProfileView fetchGeneralProfile(String profileId) {
    CompletableFuture<EducationView> educationFuture = CompletableFuture.supplyAsync(() -> fetchEducations(profileId));
    CompletableFuture<PositionView> positionFuture = CompletableFuture.supplyAsync(() -> fetchExperiences(profileId));
    CompletableFuture<SkillView> skillFuture = CompletableFuture.supplyAsync(() -> fetchSkills(profileId));
    CompletableFuture<VoyagerApiProfile> linkedInProfileFuture = CompletableFuture.supplyAsync(() -> fetchUser(profileId));
    CompletableFuture<CertificationView> certificationFuture = CompletableFuture.supplyAsync(() -> fetchCertifications(profileId));

    return ProfileView.builder()
                .educationView(educationFuture.join())
                .positionView(positionFuture.join())
                .skillView(skillFuture.join())
                .profile(linkedInProfileFuture.join())
                .certificationView(certificationFuture.join())
                .build();
  }
  /**
   * Makes a request to the Voyager endpoint that returns a json containing general information of the user
   * and deserializes it into a LinkedInProfile object.
   *
   * @param profileId The id of the user.
   * @return A LinkedInProfile object.
   */
  public VoyagerApiProfile fetchUser(String profileId) {
    return voyagerApiClient.fetchProfile(
        formatCookies(storedCookies),
        getSessionID(storedCookies),
        profileId
    );

  }
  /**
   * Makes a request to the Voyager endpoint that returns a json containing positions of the user
   * and deserializes it into a PositionView object.
   *
   * @param profileId The id of the user.
   * @return A list of LinkedInPosition objects nested inside a PositionView object.
   */
  public PositionView fetchExperiences(String profileId) {
    return voyagerApiClient.fetchPositions(
        formatCookies(storedCookies),
        getSessionID(storedCookies),
        profileId
    );

  }
  /**
   * Makes a request to the Voyager endpoint that returns a json containing skills of the user
   * and deserializes it into a SkillView object.
   *
   * @param profileId The id of the user.
   * @return A list of LinkedInSkill objects nested inside a SkillView object.
   */
  public SkillView fetchSkills(String profileId) {
    return voyagerApiClient.fetchSkills(
        formatCookies(storedCookies),
        getSessionID(storedCookies),
        profileId
    );

  }
  /**
   * Makes a request to the Voyager endpoint that returns a json containing educations of the user
   * and deserializes it into an EducationView object.
   *
   * @param profileId The id of the user.
   * @return A list of LinkedInEducation objects nested inside an EducationView object.
   */
  public EducationView fetchEducations(String profileId) {
    return voyagerApiClient.fetchEducations(
        formatCookies(storedCookies),
        getSessionID(storedCookies),
        profileId
    );

  }
  /**
   * Makes a request to the Voyager endpoint that returns a json containing certifications of the user
   * and deserializes it into an CertificationView object.
   *
   * @param profileId The id of the user.
   * @return A list of LinkedInCertification objects nested inside an CertificationView object.
   */
  public CertificationView fetchCertifications(String profileId) {
    return voyagerApiClient.fetchCertifications(
        formatCookies(storedCookies),
        getSessionID(storedCookies),
        profileId
    );
  }
  /**
   * Makes a request to the Voyager endpoint that returns a json containing the information of the company
   * and deserializes it into an CompanyView object.
   *
   * @param companyId The id of the user.
   * @return CompanyView object with nested elements concerning the desired company.
   */
  public CompanyView fetchCompany(String companyId) {
    return voyagerApiClient.fetchCompanyById(
        formatCookies(storedCookies),
        getSessionID(storedCookies),
        companyId
    );
  }
  /**
   * Makes a request to the Voyager endpoint that returns a json containing the information of the school
   * and deserializes it into an VoyagerApiSchool object.
   *
   * @param schoolId The id of the user.
   * @return VoyagerApiSchool object with nested elements concerning the desired school.
   */
  public VoyagerApiSchool fetchSchool(String schoolId) {
    return voyagerApiClient.fetchSchool(
        formatCookies(storedCookies),
        getSessionID(storedCookies),
        schoolId
    );
  }
  // --------------------------------------- Helpers --------------------------------------- \\



  public static Map<String, String> getCookies(ResponseEntity<?> responseEntity) {
    HttpHeaders headers = responseEntity.getHeaders();
    List<String> cookieList = headers.get(HttpHeaders.SET_COOKIE);
    if (cookieList != null) {
      Map<String, String> cookiesMap = new HashMap<>();
      for (String cookie : cookieList) {
        String[] cookieParts = cookie.split(";\\s*");
        for (String part : cookieParts) {
          String[] keyValue = part.split("=", 2);
          if (keyValue.length == 2) {
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            cookiesMap.put(key, value);
          }
        }
      }
      return cookiesMap;
    }
    return null;
  }

  private String formatCookies(Map<String, String> storedCookies) {
    String liAt = storedCookies.get("li_at");
    String storedJSESSIONID = getSessionID(storedCookies);
    return "JSESSIONID=\"%s\"; li_at=%s".formatted(storedJSESSIONID, liAt);
  }

  private String getSessionID(Map<String, String> storedCookies) {
    return storedCookies.get("JSESSIONID").replace("\"", "");
  }
}
