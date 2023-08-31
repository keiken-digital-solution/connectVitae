package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.services;


import io.connectvitae.connectvitaelibrary.mappers.VoyagerApiMapperService;
import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Education;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.Skill;
import io.connectvitae.connectvitaelibrary.models.User;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInCertification;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInEducation;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInPosition;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInProfile;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInSkill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

public class VoyagerApiMapperServiceTest {
  @Autowired
  private VoyagerApiMapperService voyagerApiMapperService;

  @Test
  void testMapExperience() {
    LinkedInPosition linkedInPosition =
        LinkedInPosition.builder()
            .companyName("Keiken Digital")
            .description("Working on developing a intern project for skills management")
            .locationName("Paris, France")
            .title("Software Developer")
            .build();
    Experience actualExperience = voyagerApiMapperService.mapExperience(linkedInPosition);

    Experience expectedExperience =
        Experience.builder()
            .company("Keiken Digital")
            .mission("Working on developing a intern project for skills management")
            .roleName("Software Developer")
            .build();

    assertEquals(expectedExperience, actualExperience);
  }

  @Test
  void testMapCertification() {
    LinkedInCertification linkedInCertification =
        LinkedInCertification.builder()
            .authority("Adobe")
            .name("Adobe certified Expert : CQ Component Developer")
            .build();
    Certification expectedCertification =
        Certification.builder()
            .certificationProvider("Adobe")
            .certificationName("Adobe certified Expert : CQ Component Developer")
            .build();
    Certification actualCertification = voyagerApiMapperService.mapCertification(linkedInCertification);
    assertEquals(expectedCertification, actualCertification);
  }

  @Test
  void testMapTraining() {
    LinkedInEducation linkedInEducation =
        LinkedInEducation.builder()
            .degreeName("Engineer")
            .fieldOfStudy("Information Technology")
            .schoolName("INPT")
            .build();
    Education expectedEducation =
        Education.builder()
            .school("INPT")
            .specialty("Information Technology")
            .degree("Engineer")
            .build();
    Education actualEducation = voyagerApiMapperService.mapEducation(linkedInEducation);
    assertEquals(expectedEducation, actualEducation);
  }

  @Test
  void testMapSkill() {
    LinkedInSkill linkedInSkill = LinkedInSkill.builder().name("Project Management").build();
    Skill expectedSkill = Skill.builder().skillName("Project Management").build();
    Skill actualSkill = voyagerApiMapperService.mapSkill(linkedInSkill);
    assertEquals(expectedSkill, actualSkill);
  }

  @Test
  void testMapUser() {
    LinkedInProfile linkedInProfile =
        LinkedInProfile.builder()
            .firstName("John")
            .lastName("Lenon")
            .headline("Software Engineer")
            .geoLocationName("Paris, France")
            .summary(
                "An enthusiastic software engineer who is interested in artificial intelligence")
            .industryName("Information technology")
            .build();
    User expectedUser =
        User.builder()
            .firstName("John")
            .lastName("Lenon")
            .address("Paris, France")
            .bio("An enthusiastic software engineer who is interested in artificial intelligence")
            .build();
    User actualUser = voyagerApiMapperService.mapUser(linkedInProfile);
    assertEquals(expectedUser, actualUser);
  }
}
