package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services;

import io.connectvitae.connectvitaelibrary.linkedIn.services.DataExtractorServiceInterface;
import io.connectvitae.connectvitaelibrary.models.*;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataExtractorService implements DataExtractorServiceInterface {
    private final ScrapeService scrapeService;
    private final SeleniumService seleniumService;
    public Profile getProfile(String profileId) {
        return Profile.builder()
                .experiences(getExperiences(profileId))
                .educations(getEducations(profileId))
                .skills(getSkills(profileId))
                .certifications(getCertifications(profileId))
//                .user(fetchUser(profileId))  TODO: Handle the null case of profile information
                .build();
    }

    public User getUser(String profileId){
        return scrapeService.scrapeUser(seleniumService.fetchUser(profileId));
    }

    public List<Experience> getExperiences(String profileId) {
        var experiences =  seleniumService.fetchExperiences(profileId);
        List<Experience> experienceList = new ArrayList<>();
        getElements(experiences).forEach(element -> {
            experienceList.add(scrapeService.scrapeExperience(element));
        });

        return experienceList;
    }

    public List<Education> getEducations(String profileId) {
        var educations =  seleniumService.fetchEducations(profileId);
        List<Education> educationList = new ArrayList<>();
        getElements(educations).forEach(element -> {
            educationList.add(scrapeService.scrapeEducation(element));
        });

        return educationList;
    }

    public List<Skill> getSkills(String profileId) {
        var skills = seleniumService.fetchSkills(profileId);
        List<Skill> skillList = new ArrayList<>();
        getElements(skills).forEach(element -> {
            skillList.add(scrapeService.scrapeSkill(element));
        });
        return skillList;
    }

    public List<Certification> getCertifications(String profileId) {
        var certifications = seleniumService.fetchCertifications(profileId);
        List<Certification> certificationList = new ArrayList<>();
        getElements(certifications).forEach(element -> {
            certificationList.add(scrapeService.scrapeCertification(element));
        });

        return certificationList;
    }

    // --------------------------------------- Helpers --------------------------------------- \\
    public Elements getElements(String innerHTML) {
        Document doc = Jsoup.parse(innerHTML, "UTF-8");
        return doc.select("li.pvs-list__paged-list-item");
    }
}
