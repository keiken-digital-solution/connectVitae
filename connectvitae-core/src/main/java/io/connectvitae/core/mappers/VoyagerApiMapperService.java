package io.connectvitae.core.mappers;


import io.connectvitae.contracts.models.Certification;
import io.connectvitae.contracts.models.Company;
import io.connectvitae.contracts.models.Education;
import io.connectvitae.contracts.models.Experience;
import io.connectvitae.contracts.models.GeneralProfile;
import io.connectvitae.contracts.models.Location;
import io.connectvitae.contracts.models.School;
import io.connectvitae.contracts.models.Skill;
import io.connectvitae.contracts.models.User;
import io.connectvitae.contracts.services.MapperServiceInterface;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiCertification;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiCompany;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiCompanyLocation;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiEducation;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiPosition;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiProfile;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiSchool;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiSkill;
import io.connectvitae.voyagerApiProvider.models.views.ProfileView;
import org.springframework.stereotype.Service;


@Service
public class VoyagerApiMapperService implements MapperServiceInterface<
    ProfileView,
    VoyagerApiCertification,
    VoyagerApiEducation,
    VoyagerApiPosition,
    Object,
    VoyagerApiSkill,
    VoyagerApiProfile,
    VoyagerApiSchool,
    VoyagerApiCompany> {
  /**
   * Maps data from the ProfileView object to the intern Profile object that contains Experiences,
   * Educations, Skills, Certifications and User data.
   *
   * @param profileView The source general profile view object.
   * @return The mapped Profile object.
   */
  @Override
  public GeneralProfile mapGeneralProfile(ProfileView profileView) {
    return GeneralProfile.builder()
        .user(this.mapUser(profileView.getProfile()))
        .experiences(
            profileView
                .getPositionView()
                .getElements()
                .stream()
                .map(this::mapExperience)
                .toList()
        )
        .educations(
            profileView
                .getEducationView()
                .getElements()
                .stream()
                .map(this::mapEducation)
                .toList()
        )
        .skills(
            profileView
                .getSkillView()
                .getElements()
                .stream()
                .map(this::mapSkill)
                .toList()
        )
        .certifications(
            profileView
                .getCertificationView()
                .getElements()
                .stream()
                .map(this::mapCertification)
                .toList()
        )
        .build();
  }
  /**
   * Maps data from the LinkedInProfile object to the intern User object that contains
   * general information about the chosen user.
   *
   * @param voyagerApiProfile The source Profile object.
   * @return The intern model User object.
   */
  @Override
  public User mapUser(VoyagerApiProfile voyagerApiProfile) {
    return User.builder()
        .firstName(voyagerApiProfile.getFirstName())
        .lastName(voyagerApiProfile.getLastName())
        .address(voyagerApiProfile.getGeoLocationName())
        .bio(voyagerApiProfile.getSummary())
        .build();
  }
  /**
   * Maps data from the LinkedInEducation object to the intern Education object.
   *
   * @param voyagerApiEducation The source Education object.
   * @return The intern model education object.
   */
  @Override
  public Education mapEducation(VoyagerApiEducation voyagerApiEducation) {
    return Education.builder()
        .school(voyagerApiEducation.getSchoolName())
        .degree(voyagerApiEducation.getDegreeName())
        .specialty(voyagerApiEducation.getFieldOfStudy())
        .startDate(
            voyagerApiEducation.getTimePeriod() != null
                ? voyagerApiEducation.getTimePeriod().getStartDate()
                : null
        )
        .endDate(
            voyagerApiEducation.getTimePeriod() != null
                ? voyagerApiEducation.getTimePeriod().getEndDate()
                : null
        )
        .build();
  }
  /**
   * Maps data from the LinkedInSkill object to the intern Skill object.
   *
   * @param voyagerApiSkill The source Skill object.
   * @return The intern model Skill object.
   */
  @Override
  public Skill mapSkill(VoyagerApiSkill voyagerApiSkill) {
    return Skill.builder()
        .skillName(voyagerApiSkill.getName()).build();
  }
  /**
   * Maps data from the LinkedInPosition object to the intern Experience object.
   *
   * @param voyagerApiPosition The source Certification object.
   * @return The intern model Experience object.
   */
  @Override
  public Experience mapExperience(VoyagerApiPosition voyagerApiPosition) {
    return Experience.builder()
        .startDate(
            voyagerApiPosition.getTimePeriod() != null
                ? voyagerApiPosition.getTimePeriod().getStartDate()
                : null
        )
        .endDate(
            voyagerApiPosition.getTimePeriod() != null
                ? voyagerApiPosition.getTimePeriod().getEndDate()
                : null
        )
        .company(voyagerApiPosition.getCompanyName())
        .roleName(voyagerApiPosition.getTitle())
        .mission(voyagerApiPosition.getDescription())
        .build();
  }
  /**
   * Maps data from the LinkedInCertification object to the intern Certification object.
   *
   * @param voyagerApiCertification The source Certification object.
   * @return The intern model certification object.
   */
  @Override
  public Certification mapCertification(VoyagerApiCertification voyagerApiCertification) {
    return Certification.builder()
        .certificationName(voyagerApiCertification.getName())
        .certifiedDate(
            voyagerApiCertification.getTimePeriod() != null
                ? voyagerApiCertification.getTimePeriod().getStartDate()
                : null
        )
        .certificationProvider(voyagerApiCertification.getAuthority())
        .build();
  }
  /**
   * Maps data from the VoyagerApiCompany object to the intern Company object.
   *
   * @param voyagerApiCompany The source Company object.
   * @return The intern model Company object.
   */
  @Override
  public Company mapCompany(VoyagerApiCompany voyagerApiCompany) {
    return Company.builder()
          .companyName(voyagerApiCompany.getName())
          .companyWebSiteUrl(voyagerApiCompany.getCompanyPageUrl())
          .companyEmployeeCountRange(voyagerApiCompany.getStaffCount())
          .companyPhoneNumber(
                  voyagerApiCompany.getPhone() != null
                          ? voyagerApiCompany.getPhone().getNumber()
                          : null
          )
          .companySpecialities(voyagerApiCompany.getSpecialities())
          .companyDescription(voyagerApiCompany.getDescription())
          .companyLocations(voyagerApiCompany.getConfirmedLocations()
              .stream()
              .map(VoyagerApiMapperService::mapLocation)
              .toList())
          .companyType(
              voyagerApiCompany.getCompanyType() != null
                ? voyagerApiCompany.getCompanyType().getLocalizedName()
                : null)
          .build();
  }
  /**
   * Maps data from the VoyagerApiSchool object to the intern School object.
   *
   * @param voyagerApiSchool The source School object.
   * @return The intern model School object.
   */
  @Override
  public School mapSchool(VoyagerApiSchool voyagerApiSchool) {

    return School.builder()
        .schoolName(voyagerApiSchool.getSchoolName())
        .schoolType(voyagerApiSchool.getSchoolType())
        .schoolAddress(voyagerApiSchool.getAddress())
        .schoolNumber(voyagerApiSchool.getPhoneNumber() != null
            ? voyagerApiSchool.getPhoneNumber().getNumber()
            : null)
        .schoolUrl(voyagerApiSchool.getHomepageUrl())
        .schoolDescription(voyagerApiSchool.getDescription())
        .build();
  }
  public static Location mapLocation(VoyagerApiCompanyLocation voyagerApiCompanyLocation) {
    return Location.builder()
        .city(voyagerApiCompanyLocation.getCity())
        .country(voyagerApiCompanyLocation.getCountry())
        .description(voyagerApiCompanyLocation.getDescription())
        .geographicArea(voyagerApiCompanyLocation.getGeographicArea())
        .postalCode(voyagerApiCompanyLocation.getPostalCode())
        .line1(voyagerApiCompanyLocation.getLine1())
        .line2(voyagerApiCompanyLocation.getLine2())
        .build();
  }

}
