package io.connectvitae.connectvitaelibrary.providers.seleniumProvider.services;

import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumCertification;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumEducation;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumExperience;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumLanguage;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumGeneralProfile;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumSkill;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumUser;
import io.connectvitae.connectvitaelibrary.services.ExtractorServiceInterface;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeleniumExtractorService implements ExtractorServiceInterface {
  private final SeleniumScrapeService seleniumScrapeService;
  private final SeleniumFetcherService seleniumFetcherService;

  @Override
  public SeleniumGeneralProfile getProfile(String profileId) {
    return SeleniumGeneralProfile.builder()
        .seleniumExperiences(getExperiences(profileId))
        .seleniumEducations(getEducations(profileId))
        .seleniumSkills(getSkills(profileId))
        .seleniumCertifications(getCertifications(profileId))
        .seleniumLanguages(getLanguages(profileId))
        .seleniumUser(getUser(profileId))
        .build();
  }
  @Override
  public SeleniumUser getUser(String profileId) {
    return seleniumScrapeService.scrapeUser(seleniumFetcherService.fetchUser(profileId));
  }
  @Override
  public List<SeleniumExperience> getExperiences(String profileId) {
    var experiences = seleniumFetcherService.fetchExperiences(profileId);
    List<SeleniumExperience> seleniumExperienceList = new ArrayList<>();
    getElements(experiences).forEach(element -> {
      boolean b = isExperienceGroup(element)
          ? seleniumExperienceList.addAll(seleniumScrapeService.scrapeExperiencesGroup(element))
          : seleniumExperienceList.add(seleniumScrapeService.scrapeExperience(element));
    });

    return seleniumExperienceList;
  }
  @Override
  public List<SeleniumEducation> getEducations(String profileId) {
    var educations = seleniumFetcherService.fetchEducations(profileId);
    List<SeleniumEducation> seleniumEducationList = new ArrayList<>();
    getElements(educations).forEach(element -> {
      seleniumEducationList.add(seleniumScrapeService.scrapeEducation(element));
    });

    return seleniumEducationList;
  }
  public List<SeleniumLanguage> getLanguages(String profileId) {
    var languages = seleniumFetcherService.fetchLanguages(profileId);
    List<SeleniumLanguage> seleniumLanguageList = new ArrayList<>();
    getElements(languages).forEach(element -> {
      seleniumLanguageList.add(seleniumScrapeService.scrapeLanguage(element));
    });
    return seleniumLanguageList;
  }
  @Override
  public List<SeleniumSkill> getSkills(String profileId) {
    var skills = seleniumFetcherService.fetchSkills(profileId);
    List<SeleniumSkill> seleniumSkillList = new ArrayList<>();
    getElements(skills).forEach(element -> {
      seleniumSkillList.add(seleniumScrapeService.scrapeSkill(element));
    });
    return seleniumSkillList;
  }
  @Override
  public List<SeleniumCertification> getCertifications(String profileId) {
    var certifications = seleniumFetcherService.fetchCertifications(profileId);
    List<SeleniumCertification> seleniumCertificationList = new ArrayList<>();
    getElements(certifications).forEach(element -> {
      seleniumCertificationList.add(seleniumScrapeService.scrapeCertification(element));
    });

    return seleniumCertificationList;
  }

  // --------------------------------------- Helpers --------------------------------------- \\
  public Elements getElements(String innerHTML) {
    Document doc = Jsoup.parse(innerHTML, "UTF-8");
    return doc.select("li.pvs-list__paged-list-item");
  }
  public boolean isExperienceGroup(Element element) {
    Elements selectedElements = element.select(".t-bold");
    return selectedElements.size() != 1;
  }
}
