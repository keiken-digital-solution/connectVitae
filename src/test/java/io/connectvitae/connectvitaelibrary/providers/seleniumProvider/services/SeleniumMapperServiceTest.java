package io.connectvitae.connectvitaelibrary.providers.seleniumProvider.services;

import io.connectvitae.connectvitaelibrary.mappers.SeleniumMapperService;
import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Education;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.Language;
import io.connectvitae.connectvitaelibrary.models.Skill;
import io.connectvitae.connectvitaelibrary.models.User;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumCertification;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumEducation;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumExperience;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumLanguage;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumSkill;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
//TODO: implement the test methods with real information
@SpringBootTest
public class SeleniumMapperServiceTest {
  private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
  @Autowired
  private SeleniumMapperService seleniumMapperService;
  @Test
  public void testMapExperience() throws ParseException {
    SeleniumExperience seleniumExperience = SeleniumExperience.builder()
        .company("Keiken Digital")
        .mission("Working on developing a intern project for skills management")
        .roleName("Software Developer")
        .location("Paris, France")
        .startDate(FORMATTER.parse("01/02/2015"))
        .endDate(FORMATTER.parse("01/06/2015"))
        .build();
    Experience actualExperience = seleniumMapperService.mapExperience(seleniumExperience);
    Experience expectedExperience = Experience.builder()
        .company("Keiken Digital")
        .mission("Working on developing a intern project for skills management")
        .roleName("Software Developer")
        .location("Paris, France")
        .startDate(FORMATTER.parse("01/02/2015"))
        .endDate(FORMATTER.parse("01/06/2015")).build();
    assertEquals(actualExperience, expectedExperience);
  }
  @Test
  public void testMapEducation() throws ParseException {
    SeleniumEducation seleniumEducation = SeleniumEducation.builder()
        .degree("Engineer")
        .specialty("Information Technology")
        .school("INPT")
        .grade("Excellent")
        .startDate(FORMATTER.parse("01/09/2015"))
        .endDate(FORMATTER.parse("01/02/2018"))
        .build();
    Education actualEducation = seleniumMapperService.mapEducation(seleniumEducation);
    Education expectedEducation = Education.builder()
        .degree("Engineer")
        .specialty("Information Technology")
        .school("INPT")
        .grade("Excellent")
        .startDate(FORMATTER.parse("01/09/2015"))
        .endDate(FORMATTER.parse("01/02/2018"))
        .build();
    assertEquals(actualEducation, expectedEducation);
  }
  @Test
  public void testMapSkill() {
    SeleniumSkill seleniumSkill = SeleniumSkill.builder()
        .skillName("Project Management")
        .skillEndorsements(Arrays.asList("Skill Confirmed by 3 Project Managers",
            "Skill confirmed by 20 friends"))
        .build();
    Skill actualSkill = seleniumMapperService.mapSkill(seleniumSkill);
    Skill expectedSkill = Skill.builder()
        .skillName("Project Management")
        .skillEndorsements(Arrays.asList("Skill Confirmed by 3 Project Managers",
            "Skill confirmed by 20 friends"))
        .build();
    assertEquals(actualSkill, expectedSkill);
  }
  @Test
  public void testMapCertification() throws ParseException {
    SeleniumCertification seleniumCertification = SeleniumCertification.builder()
        .certificationName("Adobe certified Expert : CQ Component Developer")
        .certificationProvider("Adobe")
        .certifiedDate(FORMATTER.parse("01/02/2018"))
        .build();
    Certification actualCertification = seleniumMapperService.mapCertification(seleniumCertification);
    Certification expectedCertification = Certification.builder()
        .certificationName("Adobe certified Expert : CQ Component Developer")
        .certificationProvider("Adobe")
        .certifiedDate(FORMATTER.parse("01/02/2018"))
        .build();
    assertEquals(actualCertification, expectedCertification);
  }
  @Test
  public void testMapUser() {
    SeleniumUser seleniumUser = SeleniumUser.builder()
        .firstName("John")
        .lastName("Ronald")
        .email("john_ronald@mail.com")
        .address("London, England")
        .bio("An enthusiastic software engineer who is interested in artificial intelligence")
        .build();
    User actualUser = seleniumMapperService.mapUser(seleniumUser);
    User expectedUser = User.builder()
        .firstName("John")
        .lastName("Ronald")
        .email("john_ronald@mail.com")
        .address("London, England")
        .bio("An enthusiastic software engineer who is interested in artificial intelligence")
        .build();
    assertEquals(expectedUser, actualUser);
  }
  @Test
  public void testMapLanguage() {
    SeleniumLanguage seleniumLanguage = SeleniumLanguage.builder()
        .languageName("English")
        .languageLevel("Fluent - Native Language")
        .build();
    Language actualLanguage = seleniumMapperService.mapLanguage(seleniumLanguage);
    Language expectedLanguage = Language.builder()
        .languageName("English")
        .languageLevel("Fluent - Native Language")
        .build();
    assertEquals(actualLanguage, expectedLanguage);
  }
}
