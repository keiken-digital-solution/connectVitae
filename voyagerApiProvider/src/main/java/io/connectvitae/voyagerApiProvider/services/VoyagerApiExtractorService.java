package io.connectvitae.voyagerApiProvider.services;



import io.connectvitae.contracts.services.ExtractorServiceInterface;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiCertification;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiEducation;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiPosition;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiProfile;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiSchool;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiSkill;
import io.connectvitae.voyagerApiProvider.models.views.CompanyView;
import io.connectvitae.voyagerApiProvider.models.views.ProfileView;
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
  @Override
  public VoyagerApiSchool getSchool(String schoolId) {
    return fetcherService.fetchSchool(schoolId);
  }
}
