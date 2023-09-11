package io.connectvitae.connectvitaelibrary.mappers;

import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Education;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.Language;
import io.connectvitae.connectvitaelibrary.models.GeneralProfile;
import io.connectvitae.connectvitaelibrary.models.Skill;
import io.connectvitae.connectvitaelibrary.models.User;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumCertification;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumEducation;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumExperience;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumLanguage;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumGeneralProfile;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumSkill;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumUser;
import io.connectvitae.connectvitaelibrary.services.MapperServiceInterface;
import org.springframework.stereotype.Service;
@Service
public class SeleniumMapperService implements MapperServiceInterface<
    SeleniumGeneralProfile,
    SeleniumCertification,
    SeleniumEducation,
    SeleniumExperience,
    SeleniumLanguage,
    SeleniumSkill,
    SeleniumUser,
    Object,
    Object> {
  /**
   * Maps data from the SeleniumProfile object to the intern Profile object that contains Experiences,
   * Educations, Skills, Certifications and User data.
   *
   * @param profile The source general profile view object.
   * @return The mapped Profile object.
   */
  @Override
  public GeneralProfile mapGeneralProfile(SeleniumGeneralProfile profile) {
    return GeneralProfile.builder()
        .user(this.mapUser(profile.getSeleniumUser()))
        .experiences(
            profile
                .getSeleniumExperiences()
                .stream()
                .map(this::mapExperience)
                .toList()
        )
        .educations(
            profile
                .getSeleniumEducations()
                .stream()
                .map(this::mapEducation)
                .toList()
        )
        .skills(
            profile
                .getSeleniumSkills()
                .stream()
                .map(this::mapSkill)
                .toList()
        )
        .certifications(
            profile
                .getSeleniumCertifications()
                .stream()
                .map(this::mapCertification)
                .toList()
        )
        .languages(profile
            .getSeleniumLanguages()
            .stream()
            .map(this::mapLanguage)
            .toList())
        .build();
  }
  /**
   * Maps data from the SeleniumProfile object to the intern User object that contains
   * general information about the chosen user.
   *
   * @param seleniumUser The source Profile object.
   * @return The intern model User object.
   */
  @Override
  public User mapUser(SeleniumUser seleniumUser) {
    return User.builder()
        .firstName(seleniumUser.getFirstName())
        .lastName(seleniumUser.getLastName())
        .address(seleniumUser.getAddress())
        .bio(seleniumUser.getBio())
        .email(seleniumUser.getEmail())
        .build();
  }
  /**
   * Maps data from the SeleniumEducation object to the intern Education object.
   *
   * @param seleniumEducation The source Education object.
   * @return The intern model education object.
   */
  @Override
  public Education mapEducation(SeleniumEducation seleniumEducation) {
    return Education.builder()
        .school(seleniumEducation.getSchool())
        .degree(seleniumEducation.getDegree())
        .specialty(seleniumEducation.getSpecialty())
        .startDate(seleniumEducation.getStartDate())
        .endDate(seleniumEducation.getEndDate())
        .grade(seleniumEducation.getGrade())
        .build();
  }
  /**
   * Maps data from the SeleniumInSkill object to the intern Skill object.
   *
   * @param seleniumSkill The source Skill object.
   * @return The intern model Skill object.
   */
  @Override
  public Skill mapSkill(SeleniumSkill seleniumSkill) {
    return Skill.builder()
        .skillName(seleniumSkill.getSkillName())
        .skillEndorsements(seleniumSkill.getSkillEndorsements())
        .build();
  }
  /**
   * Maps data from the SeleniumPosition object to the intern Experience object.
   *
   * @param seleniumExperience The source Certification object.
   * @return The intern model Experience object.
   */
  @Override
  public Experience mapExperience(SeleniumExperience seleniumExperience) {
    return Experience.builder()
        .startDate(seleniumExperience.getStartDate())
        .endDate(seleniumExperience.getEndDate())
        .company(seleniumExperience.getCompany())
        .roleName(seleniumExperience.getRoleName())
        .mission(seleniumExperience.getMission())
        .location(seleniumExperience.getLocation())
        .build();
  }
  /**
   * Maps data from the SeleniumCertification object to the intern Certification object.
   *
   * @param seleniumCertification The source Certification object.
   * @return The intern model certification object.
   */
  @Override
  public Certification mapCertification(SeleniumCertification seleniumCertification) {
    return Certification.builder()
        .certificationName(seleniumCertification.getCertificationName())
        .certifiedDate(seleniumCertification.getCertifiedDate())
        .certificationProvider(seleniumCertification.getCertificationProvider())
        .build();
  }
  /**
   * Maps data from the SeleniumLanguage object to the intern Language object.
   *
   * @param seleniumLanguage The source Certification object.
   * @return The intern model language object.
   */
  @Override
  public Language mapLanguage(SeleniumLanguage seleniumLanguage) {
    return Language.builder()
        .languageName(seleniumLanguage.getLanguageName())
        .languageLevel(seleniumLanguage.getLanguageLevel())
        .build();
  }
}
