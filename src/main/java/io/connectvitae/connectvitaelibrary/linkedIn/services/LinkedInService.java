package io.connectvitae.connectvitaelibrary.linkedIn.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInAuthenticationDTO;
import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInProfile;
import io.connectvitae.connectvitaelibrary.linkedIn.models.views.*;
import io.connectvitae.connectvitaelibrary.linkedIn.proxies.LinkedInClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LinkedInService {

    private final LinkedInClient linkedinClient;
    // TODO: find a better way to handle this
    private Map<String, String> storedCookies;

    public void authenticate(String username, String password) {
         ResponseEntity<String> authenticationResponse =
                 linkedinClient.authenticate(
                         "LIAuthLibrary:3.2.4 com.linkedin.LinkedIn:8.8.1 iPhone:8.3",
                         LinkedInAuthenticationDTO.builder()
                                 .session_key(username)
                                 .session_password(password)
                                 .build()
                         );

        storedCookies = getCookies(authenticationResponse);

        // Logging the response body
        System.out.println(authenticationResponse.getBody());
    }

    public ProfileView fetchProfileView(String profileId) {
        return linkedinClient.fetchProfileView(
                formatCookies(storedCookies),
                getSessionID(storedCookies),
                profileId
        );
    }

    // Making profile view from different retrieved complete views
    public ProfileView getProfileView(String profileId) {
        return ProfileView.builder()
                .educationView(fetchEducations(profileId))
                .positionView(fetchPositions(profileId))
                .skillView(fetchSkills(profileId))
                .profile(fetchProfile(profileId))
                .certificationView(fetchCertifications(profileId))
                .build();
    }

    // TODO: Change the method name
    public LinkedInProfile fetchProfile(String profileId) {
        return linkedinClient.fetchProfile(
                formatCookies(storedCookies),
                getSessionID(storedCookies),
                profileId
        );

    }

    public PositionView fetchPositions(String profileId) {
        return linkedinClient.fetchPositions(
                formatCookies(storedCookies),
                getSessionID(storedCookies),
                profileId
        );

    }

    public SkillView fetchSkills(String profileId) {
        return linkedinClient.fetchSkills(
                formatCookies(storedCookies),
                getSessionID(storedCookies),
                profileId
        );

    }

    public EducationView fetchEducations(String profileId) {
        return linkedinClient.fetchEducations(
                formatCookies(storedCookies),
                getSessionID(storedCookies),
                profileId
        );

    }

    public CertificationView fetchCertifications(String profileId) {
        return linkedinClient.fetchCertifications(
                formatCookies(storedCookies),
                getSessionID(storedCookies),
                profileId
        );

    }

    private String formatCookies(Map<String, String> storedCookies) {
        String liAt = storedCookies.get("li_at");
        String storedJSESSIONID = getSessionID(storedCookies);
        return "JSESSIONID=\"%s\"; li_at=%s".formatted(storedJSESSIONID, liAt);
    }

    private String getSessionID(Map<String, String> storedCookies) {
        return storedCookies.get("JSESSIONID").replace("\"", "");
    }

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
}
