package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.mappers;


import io.connectvitae.connectvitaelibrary.models.*;

import java.util.function.Function;

import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.*;
import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.views.ProfileView;
import org.springframework.stereotype.Component;


@Component
public class ProfileMapper implements Function<ProfileView, Profile> {


    @Override
    public Profile apply(ProfileView profileView) {
        return Profile.builder()
                .user(this.convertToUser(profileView.getProfile()))
                .experiences(
                        profileView
                                .getPositionView()
                                .getElements()
                                .stream()
                                .map(this::convertToExperience)
                                .toList()
                )
                .educations(
                        profileView
                                .getEducationView()
                                .getElements()
                                .stream()
                                .map(this::convertToTraining)
                                .toList()
                )
                .skills(
                        profileView
                                .getSkillView()
                                .getElements()
                                .stream()
                                .map(this::convertToSkill)
                                .toList()
                )
                .certifications(
                        profileView
                                .getCertificationView()
                                .getElements()
                                .stream()
                                .map(this::convertToCertification)
                                .toList()
                )
                .build();
    }

  public User convertToUser(LinkedInProfile linkedInProfile) {
    return User.builder()
        .firstName(linkedInProfile.getFirstName())
        .lastName(linkedInProfile.getLastName())
        .adresse(linkedInProfile.getGeoLocationName())
        .bio(linkedInProfile.getSummary())
        .build();
  }


    public Education convertToTraining(LinkedInEducation linkedInEducation) {
        return Education.builder()
                .school(linkedInEducation.getSchoolName())
                .degree(linkedInEducation.getDegreeName())
                .specialty(linkedInEducation.getFieldOfStudy())
                .startDate(
                        linkedInEducation.getTimePeriod() != null ?
                                linkedInEducation.getTimePeriod().getStartDate()
                                : null
                )
                .endDate(
                        linkedInEducation.getTimePeriod() != null ?
                                linkedInEducation.getTimePeriod().getEndDate()
                                : null
        )
        .build();
  }

  public Skill convertToSkill(LinkedInSkill linkedInSkill) {
    return Skill.builder()
        .skillName(linkedInSkill.getName()).build();
  }

  public Experience convertToExperience(LinkedInPosition linkedInPosition) {
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

  public Certification convertToCertification(LinkedInCertification linkedInCertification) {
    return Certification.builder()
        .certificationName(linkedInCertification.getName())
        .certifiedDate(
            linkedInCertification.getTimePeriod() != null
                ? linkedInCertification.getTimePeriod().getEndDate()
                : null
        )
        .certificationProvider(
            linkedInCertification.getAuthority() != null
                ? linkedInCertification.getAuthority()
                : linkedInCertification.getCompany() != null
                ? linkedInCertification.getCompany().getName()
                : null
        )
        .build();
  }
}
