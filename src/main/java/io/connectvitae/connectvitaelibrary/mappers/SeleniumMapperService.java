package io.connectvitae.connectvitaelibrary.mappers;

import io.connectvitae.connectvitaelibrary.models.*;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.*;

import java.util.function.Function;

public class SeleniumMapperService implements Function<SeleniumProfile, Profile> {
    /**
     * Maps data from the ProfileView object to the intern Profile object that contains Experiences,
     * Educations, Skills, Certifications and User data.
     *
     * @param profile The source general profile view object.
     * @return The mapped Profile object.
     */

    @Override
    public Profile apply(SeleniumProfile profile) {
        return Profile.builder()
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
                .build();
    }

    /**
     * Maps data from the LinkedInProfile object to the intern User object that contains
     * general information about the chosen user.
     *
     * @param seleniumUser The source Profile object.
     * @return The intern model User object.
     */
    public User mapUser(SeleniumUser seleniumUser) {
        return User.builder()
                .firstName(seleniumUser.getFirstName())
                .lastName(seleniumUser.getLastName())
                .address(seleniumUser.getAddress())
                .bio(seleniumUser.getBio())
                .build();
    }


    /**
     * Maps data from the LinkedInEducation object to the intern Education object.
     *
     * @param seleniumEducation The source Education object.
     * @return The intern model education object.
     */
    public Education mapEducation(SeleniumEducation seleniumEducation) {
        return Education.builder()
                .school(seleniumEducation.getSchool())
                .degree(seleniumEducation.getDegree())
                .specialty(seleniumEducation.getSpecialty())
                .startDate(seleniumEducation.getStartDate())
                .endDate(seleniumEducation.getEndDate())
                .build();
    }


    /**
     * Maps data from the LinkedInSkill object to the intern Skill object.
     *
     * @param seleniumSkill The source Skill object.
     * @return The intern model Skill object.
     */
    public Skill mapSkill(SeleniumSkill seleniumSkill) {
        return Skill.builder()
                .skillName(seleniumSkill.getSkillName())
                .build();
    }

    /**
     * Maps data from the LinkedInPosition object to the intern Experience object.
     *
     * @param seleniumExperience The source Certification object.
     * @return The intern model Experience object.
     */
    public Experience mapExperience(SeleniumExperience seleniumExperience) {
        return Experience.builder()
                .startDate(seleniumExperience.getStartDate())
                .endDate(seleniumExperience.getEndDate())
                .company(seleniumExperience.getCompany())
                .roleName(seleniumExperience.getRoleName())
                .mission(seleniumExperience.getMission())
                .build();
    }

    /**
     * Maps data from the LinkedInCertification object to the intern Certification object.
     *
     * @param seleniumCertification The source Certification object.
     * @return The intern model certification object.
     */
    public Certification mapCertification(SeleniumCertification seleniumCertification) {
        return Certification.builder()
                .certificationName(seleniumCertification.getCertificationName())
                .certifiedDate(seleniumCertification.getCertifiedDate())
                .certificationProvider(seleniumCertification.getCertificationProvider())
                .build();
    }


}

