package io.connectvitae.seleniumProvider.services;


import io.connectvitae.seleniumProvider.models.SeleniumCertification;
import io.connectvitae.seleniumProvider.models.SeleniumEducation;
import io.connectvitae.seleniumProvider.models.SeleniumExperience;
import io.connectvitae.seleniumProvider.models.SeleniumLanguage;
import io.connectvitae.seleniumProvider.models.SeleniumSkill;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@SuppressWarnings("checkstyle:LineLength")
public class SeleniumScrapeServiceTest {
  private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
  @Autowired
  private SeleniumScrapeService seleniumScrapeService;

  @Test
  public void testScrapeExperience() throws IOException, ParseException {
    Element linkedInExperienceElement =
        getElement("linkedIn/seleniumProvider/elements/linkedin-experience-element.html");


    SeleniumExperience expectedExperience = SeleniumExperience.builder()
        .startDate(FORMATTER.parse("01/06/2016"))
        .endDate(FORMATTER.parse("01/08/2016"))
        .company("Société de Fabrication des Cuisinières (SOFACUIS) . Stage")
        .roleName("Concepteur mécanique")
        .mission(
            "Conception d'un model de four avec de nouvelles dimensions: - Etude des étapes de production des fours et cuisinières. - Conception des pièces (CATIA V5, SOLIDWORKS). - Validation de la conception avec le responsable.")
        .location("Fes -Maroc")
        .build();

    SeleniumExperience actualExperience = seleniumScrapeService.scrapeExperience(linkedInExperienceElement);

    assertEquals(expectedExperience, actualExperience);
  }

  @Test
  public void testScrapeSkill() throws IOException {
    List<String> skillEndorsements = new ArrayList<>();
    skillEndorsements.add("Compétences recommandées par Abdelahad SATOUR et 1 autre personne très compétente dans ce domaine");
    skillEndorsements.add("Compétences recommandées par 5 collègues chez Capgemini");
    Element linkedInSkillElement =
        getElement("linkedIn/seleniumProvider/elements/linkedin-skill-element.html");

    SeleniumSkill expectedSkill = SeleniumSkill.builder()
        .skillName("Java Enterprise Edition")
        .skillEndorsements(skillEndorsements)
        .build();

    SeleniumSkill actualSkill = seleniumScrapeService.scrapeSkill(linkedInSkillElement);

    assertEquals(expectedSkill, actualSkill);
  }

  @Test
  public void testScrapeCertification() throws IOException, ParseException {
    Element linkedInCertificationElement =
        getElement("linkedIn/seleniumProvider/elements/linkedin-certification-element.html");
    SeleniumCertification expectedCertification = SeleniumCertification.builder()
        .certificationName("Adobe Certified Master - Adobe Experience Manager Sites Architect")
        .certificationProvider("Adobe")
        .certifiedDate(FORMATTER.parse("01/06/2020"))
        .build();
    SeleniumCertification actualCertification = seleniumScrapeService.scrapeCertification(linkedInCertificationElement);
    assertEquals(expectedCertification, actualCertification);
  }

  @Test
  public void testScrapeLanguage() throws IOException, ParseException {
    Element linkedInLanguageElement =
        getElement("linkedIn/seleniumProvider/elements/linkedin-language-element.html");
    SeleniumLanguage expectedLanguage = SeleniumLanguage.builder()
        .languageName("Espagnol")
        .languageLevel("Elementary proficiency")
        .build();
    SeleniumLanguage actualLanguage = seleniumScrapeService.scrapeLanguage(linkedInLanguageElement);
    assertEquals(expectedLanguage, actualLanguage);
  }

  @Test
  public void testScrapeEducation() throws IOException, ParseException {
    Element linkedInEducationElement =
        getElement("linkedIn/seleniumProvider/elements/linkedin-education-element.html");

    SeleniumEducation expectedEducation = SeleniumEducation.builder()
        .school("Université Hassan II de Casablanca")
        .degree("Associate's degree, software engineering")
        .startDate(FORMATTER.parse("01/01/2005"))
        .endDate(FORMATTER.parse("01/01/2007"))
        .grade("Excellent")
        .build();
    SeleniumEducation actualEducation = seleniumScrapeService.scrapeEducation(linkedInEducationElement);
    assertEquals(expectedEducation, actualEducation);
  }

  @Test
  public void testScrapeExperienceGroup() throws IOException, ParseException {
    Element linkedInExperienceGroupElement =
        getElement("linkedIn/seleniumProvider/elements/linkedin-experience-group-element.html");

    List<SeleniumExperience> expectedExperienceGroup = new ArrayList<>();

    SeleniumExperience firstExperience = SeleniumExperience.builder()
        .startDate(FORMATTER.parse("01/11/2015"))
        .endDate(FORMATTER.parse("01/03/2016"))
        .company("Google")
        .roleName("Senior Research Scientist")
        .mission(
            "As a member of the Google Brain team, I work on deep learning, both in terms of basic research and in terms of improving products. I'm writing a textbook on deep learning along with my PhD thesis advisors: www.deeplearningbook.org")
        .location("")
        .build();

    SeleniumExperience secondExperience = SeleniumExperience.builder()
        .startDate(FORMATTER.parse("01/07/2014"))
        .endDate(FORMATTER.parse("01/11/2015"))
        .company("Google")
        .roleName("Research Scientist")
        .mission(
            "During my first year and change at Google, I contributed to TensorFlow, designed a new method for generating adversarial examples and using them to improve neural networks, made a bunch of visualizations showing that neural network loss functions aren't full of scary obstacles after all, supervised intern Tianqi Chen as he developed a method for rapidly transferring knowledge between neural networks, and did many other fun deep learning-related things.")
        .location("Mountain View, CA")
        .build();

    expectedExperienceGroup.add(firstExperience);
    expectedExperienceGroup.add(secondExperience);

    List<SeleniumExperience> actualExperiences = seleniumScrapeService.scrapeExperiencesGroup(linkedInExperienceGroupElement);
    assertEquals(expectedExperienceGroup, actualExperiences);
  }

  public Element getElement(String filePath) throws IOException {

    ClassPathResource resource = new ClassPathResource(filePath);
    String elementAsText =
        StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    return Jsoup.parse(elementAsText, "UTF-8");


  }
}
