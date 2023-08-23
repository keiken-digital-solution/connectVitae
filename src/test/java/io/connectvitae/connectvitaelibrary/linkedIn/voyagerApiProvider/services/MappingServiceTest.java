package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.services;

import io.connectvitae.connectvitaelibrary.models.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest

public class MappingServiceTest {
    @Autowired
    private MappingService mappingService;

    @Test
    void testConvertExperience() {
        LinkedInPosition linkedInPosition =
                LinkedInPosition.builder()
                        .companyName("Keiken Digital")
                        .description("Working on developing a intern project for skills management")
                        .locationName("Paris, France")
                        .title("Software Developer")
                        .build();
        Experience actualExperience = mappingService.convertToExperience(linkedInPosition);

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
        Certification actualCertification = mappingService.convertToCertification(linkedInCertification);
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
        Education expectedEducation =
                Education.builder()
                        .school("INPT")
                        .specialty("Information Technology")
                        .degree("Engineer")
                        .build();
        Education actualEducation = mappingService.convertToEducation(linkedInEducation);
        assertEquals(expectedEducation, actualEducation);
    }

    @Test
    void testConvertSkill() {
        LinkedInSkill linkedInSkill = LinkedInSkill.builder().name("Project Management").build();
        Skill expectedSkill = Skill.builder().skillName("Project Management").build();
        Skill actualSkill = mappingService.convertToSkill(linkedInSkill);
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
                        .address("Paris, France")
                        .bio("An enthusiastic software engineer who is interested in artificial intelligence")
                        .build();
        User actualUser = mappingService.convertToUser(linkedInProfile);
        assertEquals(expectedUser, actualUser);
    }
}
