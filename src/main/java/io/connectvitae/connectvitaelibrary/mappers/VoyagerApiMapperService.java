package io.connectvitae.connectvitaelibrary.mappers;


import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Company;
import io.connectvitae.connectvitaelibrary.models.Education;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.Profile;
import io.connectvitae.connectvitaelibrary.models.School;
import io.connectvitae.connectvitaelibrary.models.Skill;
import io.connectvitae.connectvitaelibrary.models.User;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInCertification;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInCompany;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInEducation;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInPosition;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInProfile;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInSchool;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInSkill;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.ProfileView;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class VoyagerApiMapperService implements Function<ProfileView, Profile> {
  /**
   * Maps data from the ProfileView object to the intern Profile object that contains Experiences,
   * Educations, Skills, Certifications and User data.
   *
   * @param profileView The source general profile view object.
   * @return The mapped Profile object.
   */

  @Override
  public Profile apply(ProfileView profileView) {
    return Profile.builder()
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
   * @param linkedInProfile The source Profile object.
   * @return The intern model User object.
   */
  public User mapUser(LinkedInProfile linkedInProfile) {
    return User.builder()
        .firstName(linkedInProfile.getFirstName())
        .lastName(linkedInProfile.getLastName())
        .address(linkedInProfile.getGeoLocationName())
        .bio(linkedInProfile.getSummary())
        .build();
  }


  /**
   * Maps data from the LinkedInEducation object to the intern Education object.
   *
   * @param linkedInEducation The source Education object.
   * @return The intern model education object.
   */
  public Education mapEducation(LinkedInEducation linkedInEducation) {
    return Education.builder()
        .school(linkedInEducation.getSchoolName())
        .degree(linkedInEducation.getDegreeName())
        .specialty(linkedInEducation.getFieldOfStudy())
        .startDate(
            linkedInEducation.getTimePeriod() != null
                ? linkedInEducation.getTimePeriod().getStartDate()
                : null
        )
        .endDate(
            linkedInEducation.getTimePeriod() != null
                ? linkedInEducation.getTimePeriod().getEndDate()
                : null
        )
        .build();
  }


  /**
   * Maps data from the LinkedInSkill object to the intern Skill object.
   *
   * @param linkedInSkill The source Skill object.
   * @return The intern model Skill object.
   */
  public Skill mapSkill(LinkedInSkill linkedInSkill) {
    return Skill.builder()
        .skillName(linkedInSkill.getName()).build();
  }

  /**
   * Maps data from the LinkedInPosition object to the intern Experience object.
   *
   * @param linkedInPosition The source Certification object.
   * @return The intern model Experience object.
   */
  public Experience mapExperience(LinkedInPosition linkedInPosition) {
    return Experience.builder()
        .startDate(
            linkedInPosition.getTimePeriod() != null
                ? linkedInPosition.getTimePeriod().getStartDate()
                : null
        )
        .endDate(
            linkedInPosition.getTimePeriod() != null
                ? linkedInPosition.getTimePeriod().getEndDate()
                : null
        )
        .company(linkedInPosition.getCompanyName())
        .roleName(linkedInPosition.getTitle())
        .mission(linkedInPosition.getDescription())
        .build();
  }

  /**
   * Maps data from the LinkedInCertification object to the intern Certification object.
   *
   * @param linkedInCertification The source Certification object.
   * @return The intern model certification object.
   */
  public Certification mapCertification(LinkedInCertification linkedInCertification) {
    return Certification.builder()
        .certificationName(linkedInCertification.getName())
        .certifiedDate(
            linkedInCertification.getTimePeriod() != null
                ? linkedInCertification.getTimePeriod().getStartDate()
                : null
        )
        .certificationProvider(linkedInCertification.getAuthority())
        .build();
  }

  public Company mapCompany(LinkedInCompany linkedInCompany) {
    return Company.builder()
          .companyName(linkedInCompany.getName())
          .companyWebSiteUrl(linkedInCompany.getCompanyPageUrl())
          .companyEmployeeCountRange(linkedInCompany.getStaffCount())
          .companyPhoneNumber(
                  linkedInCompany.getPhone() != null
                          ? linkedInCompany.getPhone().getNumber()
                          : null
          )
          .companySpecialities(linkedInCompany.getSpecialities())
          .companyDescription(linkedInCompany.getDescription())
          .companyLocations(linkedInCompany.getConfirmedLocations())
          .companyType(
              linkedInCompany.getCompanyType() != null
                ? linkedInCompany.getCompanyType().getLocalizedName()
                : null)
          .build();
  }
  public School mapSchool(LinkedInSchool linkedInSchool) {

    return School.builder()
        .schoolName(linkedInSchool.getSchoolName())
        .schoolType(linkedInSchool.getSchoolType())
        .schoolAddress(linkedInSchool.getAddress())
        .schoolNumber(linkedInSchool.getPhoneNumber() != null
            ? linkedInSchool.getPhoneNumber().getNumber()
            : null)
        .schoolUrl(linkedInSchool.getHomepageUrl())
        .schoolDescription(linkedInSchool.getDescription())
        .build();
  }

}
