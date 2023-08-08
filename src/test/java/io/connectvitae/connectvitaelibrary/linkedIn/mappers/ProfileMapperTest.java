package io.connectvitae.connectvitaelibrary.linkedIn.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.connectvitae.connectvitaelibrary.linkedIn.models.*;
import io.connectvitae.connectvitaelibrary.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProfileMapperTest {
  @Autowired private ProfileMapper profileMapper;

  @Test
  void testConvertExperience() {
    LinkedInPosition linkedInPosition =
        LinkedInPosition.builder()
            .companyName("Keiken Digital")
            .description("Working on developing a intern project for skills management")
            .locationName("Paris, France")
            .title("Software Developer")
            .build();
    Experience actualExperience = profileMapper.convertToExperience(linkedInPosition);

    Experience expectedExperience =
        Experience.builder()
            .company("Keiken Digital")
            .mission("Working on developing a intern project for skills management")
            .roleName("Software Developer")
            .build();

    assertEquals(expectedExperience, actualExperience);
  }

  @Test
  void testConvertCertification() {
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
    Certification actualCertification = profileMapper.convertToCertification(linkedInCertification);
    assertEquals(expectedCertification, actualCertification);
  }

  @Test
  void testConvertTraining() {
    LinkedInEducation linkedInEducation =
        LinkedInEducation.builder()
            .degreeName("Engineer")
            .fieldOfStudy("Information Technology")
            .schoolName("INPT")
            .build();
    Training expectedTraining =
        Training.builder()
            .school("INPT")
            .specialty("Information Technology")
            .degree("Engineer")
            .build();
    Training actualTraining = profileMapper.convertToTraining(linkedInEducation);
    assertEquals(expectedTraining, actualTraining);
  }

  @Test
  void testConvertSkill() {
    LinkedInSkill linkedInSkill = LinkedInSkill.builder().name("Project Management").build();
    Skill expectedSkill = Skill.builder().skillName("Project Management").build();
    Skill actualSkill = profileMapper.convertToSkill(linkedInSkill);
    assertEquals(expectedSkill, actualSkill);
  }

  @Test
  void testConvertUserTest() {
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
            .adresse("Paris, France")
            .bio("An enthusiastic software engineer who is interested in artificial intelligence")
            .build();
    User actualUser = profileMapper.convertToUser(linkedInProfile);
    assertEquals(expectedUser, actualUser);
  }
}
