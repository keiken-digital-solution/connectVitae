package io.connectvitae.seleniumProvider.services;


import io.connectvitae.seleniumProvider.models.*;
import io.connectvitae.seleniumProvider.utils.SeleniumDateParser;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor

public class SeleniumScrapeService {
  private final SeleniumDateParser seleniumDateParser;

  public SeleniumUser scrapeUser(String userAsHTML) {
    Document doc = Jsoup.parse(userAsHTML, "UTF-8");

    Element fullNameElement = doc.selectFirst(".pv-top-card h1");
    Elements aboutProfileCard = doc.select(
        "main [data-view-name=\"profile-card\"]:nth-of-type(2) [aria-hidden]"
    );

    String firstName = null;
    String lastName = null;
    if (fullNameElement != null) {
      String fullName = fullNameElement.text();
      firstName = fullName.split("\\s", 2)[0];
      lastName = fullName.split("\\s", 2)[1];
    }

    String about = null;
    if (aboutProfileCard.size() == 2 && aboutProfileCard.first().text().equals("About")) {
      about = aboutProfileCard.last().text();
    }

    return SeleniumUser.builder()
        .firstName(firstName)
        .lastName(lastName)
        .bio(about)
        .build();
  }
  public SeleniumExperience scrapeExperience(Element element) {

    String datesAsAText = extractText(element, 0, "t-14", "t-normal", "t-black--light");
    Date[] dates = seleniumDateParser.extractDates(datesAsAText);

    return SeleniumExperience.builder()
        .company(extractText(element, "t-14", "t-normal"))
        .roleName(extractText(element, "t-bold"))
        .mission(extractText(element, "t-14", "t-normal", "t-black"))
        .location(extractText(element, 1, "t-14", "t-normal", "t-black--light"))
        .startDate(dates[0])
        .endDate(dates[1])
        .build();
  }
  public List<SeleniumExperience> scrapeExperiencesGroup(Element experiencesGroupElement) {
    List<SeleniumExperience> seleniumExperiences = new ArrayList<>();
    String companyName = extractText(experiencesGroupElement, "t-bold");
    Elements elements = Jsoup.parse(experiencesGroupElement
                      .selectFirst("li.pvs-list__paged-list-item")
                      .html())
                    .select("li.pvs-list__paged-list-item");

    elements
        .forEach(experienceElement -> {
          String dateAsAText = extractText(experienceElement, 0, "t-14", "t-normal", "t-black--light");
          Date[] dates = seleniumDateParser.extractDates(dateAsAText);
          SeleniumExperience seleniumExperience = SeleniumExperience.builder()
              .company(companyName)
              .roleName(extractText(experienceElement, "t-bold"))
              .startDate(dates[0])
              .endDate(dates[1])
              .location(extractText(experienceElement, 1, "t-14", "t-normal", "t-black--light"))
              .mission(extractText(experienceElement, "t-14", "t-normal", "t-black"))
              .build();
          seleniumExperiences.add(seleniumExperience);
        });
    return seleniumExperiences;
  }
  public SeleniumEducation scrapeEducation(Element element) {
    String dateAsAText = extractText(element, 0, "t-14", "t-normal", "t-black--light");
    Date[] dates = seleniumDateParser.extractDates(dateAsAText);

    return SeleniumEducation.builder()
        .school(extractText(element, "t-bold"))
        .degree(extractText(element, "t-14", "t-normal"))
        .startDate(dates[0])
        .endDate(dates[1])
        .grade(getGrade(extractText(element, 0, 0, "t-14", "t-normal", "t-black")))
        .build();
  }
  public SeleniumCertification scrapeCertification(Element element) {

    String certifiedDateAsText = extractText(element, 0, "t-14", "t-normal", "t-black--light");
    if (certifiedDateAsText.startsWith("Issued ")) {
      certifiedDateAsText = certifiedDateAsText.replace("Issued ", "");
    }
    Date certifiedDate = certifiedDateAsText != ""
        ? SeleniumDateParser.parseDate(certifiedDateAsText.trim())
        : null;

    return SeleniumCertification.builder()
        .certificationName(extractText(element, "t-bold"))
        .certifiedDate(certifiedDate)
        .certificationProvider(extractText(element, "t-14", "t-normal"))
        .build();
  }
  public SeleniumSkill scrapeSkill(Element element) {
    Elements skillEndorsementsElements = element
        .select(".pv-shared-text-with-see-more.full-width.t-14.t-normal.t-black.hoverable-link-text.display-flex.align-items-center");

    List<String> skillEndorsements = new ArrayList<>();
    skillEndorsementsElements.forEach(element1 ->
        skillEndorsements.add(extractText(
            element1,
            0,
            "inline-show-more-text",
            "inline-show-more-text--is-collapsed",
            "inline-show-more-text--is-collapsed-with-line-clamp")));
    return SeleniumSkill.builder()
        .skillName(extractText(element, "t-bold"))
        .skillEndorsements(skillEndorsements)
        .build();
  }
  public SeleniumLanguage scrapeLanguage(Element element) {
    return SeleniumLanguage.builder()
        .languageName(extractText(element, "t-bold"))
        .languageLevel(extractText(element, "t-14", "t-normal", "t-black--light"))
        .build();
  }

  // --------------------------------------- Helpers --------------------------------------- \\

  private String extractText(Element element, String... selectors) {
    StringBuilder concatenatedSelector = new StringBuilder();
    for (String selector : selectors) {
      concatenatedSelector.append(".").append(selector);
    }
    Element selectedElement = element.selectFirst(concatenatedSelector.toString());
    return selectedElement != null
        ? selectedElement.child(0).text()
        : "";
  }

  private String extractText(Element element, int index, String... selectors) {
    StringBuilder concatenatedSelector = new StringBuilder();
    for (String selector : selectors) {
      concatenatedSelector.append(".").append(selector);
    }
    Elements selectedElements = element.select(concatenatedSelector.toString());
    if (index >= selectedElements.size()) {
      return "";
    }
    return selectedElements.get(index).child(0).text();
  }

  private String extractText(Element element, int index1, int index2, String... selectors) {
    StringBuilder concatenatedSelector = new StringBuilder();
    for (String selector : selectors) {
      concatenatedSelector.append(".").append(selector);
    }
    Elements selectedElements = element.select(concatenatedSelector.toString());
    if (index1 >= selectedElements.size()) {
      return "";
    }
    return selectedElements.get(index1).child(0).child(index2).text();
  }

  public static String getGrade(String text) {
    String[] parts = text.split(":");

    if (parts.length != 2) {
      return null;
    }

    String label = parts[0].trim();
    String value = parts[1].trim();

    if (label.equalsIgnoreCase("Niveau") || label.equalsIgnoreCase("Grade")) {
      return value;
    } else {
      return null;
    }
  }
}
