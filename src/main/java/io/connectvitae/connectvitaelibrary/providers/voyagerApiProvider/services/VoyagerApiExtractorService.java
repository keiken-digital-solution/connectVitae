package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.services;

import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.*;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.CompanyView;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.ProfileView;
import io.connectvitae.connectvitaelibrary.services.ExtractorServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VoyagerApiExtractorService implements ExtractorServiceInterface {

  private final VoyagerApiFetcherService fetcherService;
  @Override
  public ProfileView getProfile(String profileId) {
    return fetcherService.fetchGeneralProfile(profileId);
  }
  @Override
  public VoyagerApiProfile getUser(String profileId) {
    return fetcherService.fetchUser(profileId);
  }
  @Override
  public List<VoyagerApiPosition> getExperiences(String profileId) {
    return fetcherService.fetchExperiences(profileId).getElements();
  }
  @Override
  public List<VoyagerApiEducation> getEducations(String profileId) {
    return fetcherService.fetchEducations(profileId).getElements();
  }
  @Override
  public List<VoyagerApiSkill> getSkills(String profileId) {
    return fetcherService.fetchSkills(profileId).getElements();
  }
  @Override
  public List<VoyagerApiCertification> getCertifications(String profileId) {
    return fetcherService.fetchCertifications(profileId).getElements();
  }
  @Override
  public CompanyView getCompany(String companyId) {
    return fetcherService.fetchCompany(companyId);
  }
  public VoyagerApiSchool getSchool(String schoolId) {
    return fetcherService.fetchSchool(schoolId);
  }
}
