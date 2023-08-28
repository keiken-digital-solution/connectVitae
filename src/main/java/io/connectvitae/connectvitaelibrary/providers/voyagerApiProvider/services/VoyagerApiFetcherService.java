package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import io.connectvitae.connectvitaelibrary.mappers.VoyagerApiMappingService;
import io.connectvitae.connectvitaelibrary.services.FetcherServiceInterface;
import io.connectvitae.connectvitaelibrary.models.Profile;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInAuthenticationDTO;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInProfile;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.proxies.LinkedInClient;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.*;

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


    private final LinkedInClient linkedinClient;
    private final VoyagerApiMappingService voyagerApiMappingService;
    // TODO: find a better way to handle this
    private Map<String, String> storedCookies;

    /**
     * Gets the credentials and does an authentication request to the LinkedIn website, then stores
     * the cookies that will be used for future requests.
     *
     * @param username,password The credential for LinkedIn account with which the requests will be done.
     * @return Void.
     */
    public void authenticate(String username, String password) {
         ResponseEntity<String> authenticationResponse =
                 linkedinClient.authenticate(
                         "LIAuthLibrary:3.2.4 com.linkedin.LinkedIn:8.8.1 iPhone:8.3",
                         LinkedInAuthenticationDTO.builder()
                                 .sessionKey(username)
                                 .sessionPassword(password)
                                 .build()
                         );

        storedCookies = getCookies(authenticationResponse);

        // Logging the response body
        System.out.println(authenticationResponse.getBody());
    }

    /**
     * Makes required requests for different information, maps them to the intern data model
     * and put them in a profile object.
     *
     * @param profileId The id of the user of which we want to retrive information.
     * @return The intern model profile object.
     */
    public CompletableFuture<Profile> getProfileView(String profileId) {
        CompletableFuture<EducationView> educationFuture = CompletableFuture.supplyAsync(() -> fetchEducations(profileId));
        CompletableFuture<PositionView> positionFuture = CompletableFuture.supplyAsync(() -> fetchExperiences(profileId));
        CompletableFuture<SkillView> skillFuture = CompletableFuture.supplyAsync(() -> fetchSkills(profileId));
        CompletableFuture<LinkedInProfile> linkedInProfileFuture = CompletableFuture.supplyAsync(() -> fetchUser(profileId));
        CompletableFuture<CertificationView> certificationFuture = CompletableFuture.supplyAsync(() -> fetchCertifications(profileId));

        return CompletableFuture.allOf(
                educationFuture,
                positionFuture,
                skillFuture,
                linkedInProfileFuture,
                certificationFuture
        ).thenApplyAsync(
                ignoredVoid -> voyagerApiMappingService.apply(
                        ProfileView.builder()
                                .educationView(educationFuture.join())
                                .positionView(positionFuture.join())
                                .skillView(skillFuture.join())
                                .profile(linkedInProfileFuture.join())
                                .certificationView(certificationFuture.join())
                                .build()
                )
        );
    }

    // TODO: Change the method name
    /**
     * Makes a request to the Voyager endpoint that returns a json containing general information of the user
     * and deserializes it into a LinkedInProfile object.
     *
     * @param profileId The id of the user.
     * @return A LinkedInProfile object.
     */
    public LinkedInProfile fetchUser(String profileId) {
        return linkedinClient.fetchProfile(
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
        return linkedinClient.fetchPositions(
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
        return linkedinClient.fetchSkills(
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
        return linkedinClient.fetchEducations(
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
        return linkedinClient.fetchCertifications(
                formatCookies(storedCookies),
                getSessionID(storedCookies),
                profileId
        );

    }

    public Object fetchLanguages(String profileId){
        return null;
    }

    // --------------------------------------- Helpers --------------------------------------- \\


    static Map<String, String> getCookies(ResponseEntity<?> responseEntity) {
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


  // Making profile view from different retrieved complete views


  private String formatCookies(Map<String, String> storedCookies) {
    String liAt = storedCookies.get("li_at");
    String storedJSESSIONID = getSessionID(storedCookies);
    return "JSESSIONID=\"%s\"; li_at=%s".formatted(storedJSESSIONID, liAt);
  }

  private String getSessionID(Map<String, String> storedCookies) {
    return storedCookies.get("JSESSIONID").replace("\"", "");
  }
}
