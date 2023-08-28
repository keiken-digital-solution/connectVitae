package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.services;


import static org.junit.jupiter.api.Assertions.assertEquals;

import io.connectvitae.connectvitaelibrary.mappers.VoyagerApiMappingService;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.*;

import io.connectvitae.connectvitaelibrary.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest

public class VoyagerApiMappingServiceTest {
    @Autowired
    private VoyagerApiMappingService voyagerApiMappingService;

    @Test
    void testConvertExperience() {
        LinkedInPosition linkedInPosition =
                LinkedInPosition.builder()
                        .companyName("Keiken Digital")
                        .description("Working on developing a intern project for skills management")
                        .locationName("Paris, France")
                        .title("Software Developer")
                        .build();
        Experience actualExperience = voyagerApiMappingService.mapExperience(linkedInPosition);

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
        Certification actualCertification = voyagerApiMappingService.mapCertification(linkedInCertification);
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
        Education actualEducation = voyagerApiMappingService.mapEducation(linkedInEducation);
        assertEquals(expectedEducation, actualEducation);
    }

    @Test
    void testConvertSkill() {
        LinkedInSkill linkedInSkill = LinkedInSkill.builder().name("Project Management").build();
        Skill expectedSkill = Skill.builder().skillName("Project Management").build();
        Skill actualSkill = voyagerApiMappingService.mapSkill(linkedInSkill);
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
        User actualUser = voyagerApiMappingService.mapUser(linkedInProfile);
        assertEquals(expectedUser, actualUser);
    }
}
