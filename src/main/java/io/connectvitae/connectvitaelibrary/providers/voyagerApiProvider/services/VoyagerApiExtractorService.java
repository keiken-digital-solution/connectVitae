package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.services;

import io.connectvitae.connectvitaelibrary.models.Profile;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInCertification;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInEducation;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInPosition;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInProfile;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInSkill;
import io.connectvitae.connectvitaelibrary.services.ExtractorServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class VoyagerApiExtractorService implements ExtractorServiceInterface {

  private final VoyagerApiFetcherService fetcherService;

  public CompletableFuture<Profile> getProfile(String profileId) {
    return fetcherService.getProfileView(profileId);
  }

  public LinkedInProfile getUser(String profileId) {
    return fetcherService.fetchUser(profileId);
  }

  public List<LinkedInPosition> getExperiences(String profileId) {
    return fetcherService.fetchExperiences(profileId).getElements();
  }

  public List<LinkedInEducation> getEducations(String profileId) {
    return fetcherService.fetchEducations(profileId)
        .getElements();
  }

  public List<LinkedInSkill> getSkills(String profileId) {
    return fetcherService.fetchSkills(profileId).getElements();
  }

  public List<LinkedInCertification> getCertifications(String profileId) {
    return fetcherService.fetchCertifications(profileId).getElements();
  }
}
