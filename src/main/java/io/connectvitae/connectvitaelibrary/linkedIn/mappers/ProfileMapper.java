package io.connectvitae.connectvitaelibrary.linkedIn.mappers;

import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInCertification;
import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInEducation;
import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInPosition;
import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInProfile;
import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInSkill;
import io.connectvitae.connectvitaelibrary.linkedIn.models.views.ProfileView;
import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.Profile;
import io.connectvitae.connectvitaelibrary.models.Skill;
import io.connectvitae.connectvitaelibrary.models.Training;
import io.connectvitae.connectvitaelibrary.models.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

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
        .trainings(
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

  public Training convertToTraining(LinkedInEducation linkedInEducation) {
    return Training.builder()
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
