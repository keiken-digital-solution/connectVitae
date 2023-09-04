package io.connectvitae.connectvitaelibrary.providers.seleniumProvider.services;

import io.connectvitae.connectvitaelibrary.mappers.SeleniumMapperService;
import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Education;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.Skill;
import io.connectvitae.connectvitaelibrary.models.User;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumCertification;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumEducation;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumExperience;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumSkill;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SeleniumMapperServiceTest {
  @Autowired
  private SeleniumMapperService seleniumMapperService;
  @Test
  public void testMapExperience() {
    SeleniumExperience seleniumExperience = SeleniumExperience.builder().build();
    Experience actualExperience = seleniumMapperService.mapExperience(seleniumExperience);
    Experience expectedExperience = Experience.builder().build();
    assertEquals(actualExperience, expectedExperience);
  }
  @Test
  public void testMapEducation() {
    SeleniumEducation seleniumEducation = SeleniumEducation.builder().build();
    Education actualEducation = seleniumMapperService.mapEducation(seleniumEducation);
    Education expectedEducation = Education.builder().build();
    assertEquals(actualEducation, expectedEducation);
  }
  @Test
  public void testMapSkill() {
    SeleniumSkill seleniumSkill = SeleniumSkill.builder().build();
    Skill actualSkill = seleniumMapperService.mapSkill(seleniumSkill);
    Skill expectedSkill = Skill.builder().build();
    assertEquals(actualSkill, expectedSkill);
  }
  @Test
  public void testMapCertification() {
    SeleniumCertification seleniumCertification = SeleniumCertification.builder().build();
    Certification actualCertification = seleniumMapperService.mapCertification(seleniumCertification);
    Certification expectedCertification = Certification.builder().build();
    assertEquals(actualCertification, expectedCertification);
  }
  @Test
  public void testMapUser() {
    SeleniumUser seleniumUser = SeleniumUser.builder().build();
    User actualUser = seleniumMapperService.mapUser(seleniumUser);
    User expectedUser = User.builder().build();
    assertEquals(actualUser, expectedUser);
  }
}
