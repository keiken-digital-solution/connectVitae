package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services;

import io.connectvitae.connectvitaelibrary.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataExtractorService {
    private final ScrapeService scrapeService;
    private final SeleniumService seleniumService;
    public Profile fetchProfile(String profileId) {
        return Profile.builder()
                .experiences(fetchExperiences(profileId))
                .educations(fetchEducations(profileId))
                .skills(fetchSkills(profileId))
                .certifications(fetchCertifications(profileId))
                .user(fetchUser(profileId))
                .build();
    }

    public User fetchUser (String profileId){
        return scrapeService.scrapeUser(seleniumService.getUser(profileId));
    }
    public List<Experience> fetchExperiences(String profileId) {
        var experiences =  seleniumService.getExperiences(profileId);
        List<Experience> experienceList = new ArrayList<>();
        scrapeService.getElements(experiences).forEach(element -> {
            experienceList.add(scrapeService.scrapeExperience(element));
        });

        return experienceList;
    }

    public List<Education> fetchEducations(String profileId) {
        var educations =  seleniumService.getEducations(profileId);
        List<Education> educationList = new ArrayList<>();
        scrapeService.getElements(educations).forEach(element -> {
            educationList.add(scrapeService.scrapeEducation(element));
        });

        return educationList;
    }

    public List<Skill> fetchSkills(String profileId) {
        var skills = seleniumService.getSkills(profileId);
        List<Skill> skillList = new ArrayList<>();
        scrapeService.getElements(skills).forEach(element -> {
            skillList.add(scrapeService.scrapeSkill(element));
        });
        return skillList;
    }

    public List<Certification> fetchCertifications(String profileId) {
        var certifications = seleniumService.getCertifications(profileId);
        List<Certification> certificationList = new ArrayList<>();
        scrapeService.getElements(certifications).forEach(element -> {
            certificationList.add(scrapeService.scrapeCertification(element));
        });

        return certificationList;
    }
}
