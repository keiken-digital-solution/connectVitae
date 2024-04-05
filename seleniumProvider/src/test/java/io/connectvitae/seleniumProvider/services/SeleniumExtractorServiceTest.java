package io.connectvitae.seleniumProvider.services;

import io.connectvitae.seleniumProvider.models.SeleniumCertification;
import io.connectvitae.seleniumProvider.models.SeleniumEducation;
import io.connectvitae.seleniumProvider.models.SeleniumExperience;
import io.connectvitae.seleniumProvider.models.SeleniumLanguage;
import io.connectvitae.seleniumProvider.models.SeleniumSkill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@SuppressWarnings("checkstyle:LineLength")
public class SeleniumExtractorServiceTest {
  private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
  @MockBean
  private SeleniumFetcherService seleniumFetcherService;
  @Autowired
  private SeleniumExtractorService dataSeleniumExtractorService;


  @Test
  public void testGetEducations() throws IOException, ParseException {
    when(seleniumFetcherService.fetchEducations(anyString()))
        .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-educations.html"));
    List<SeleniumEducation> expectedEducations = Arrays.asList(
                SeleniumEducation.builder()
                    .degree("Network and System Administration/Administrator")
                    .endDate(FORMATTER.parse("01/01/2010"))
                    .startDate(FORMATTER.parse("01/01/2007"))
                    .school("Institut national des postes et telecommunications")
                    .build(),
                SeleniumEducation.builder()
                    .degree("Associate's degree, software engineering")
                    .endDate(FORMATTER.parse("01/01/2007"))
                    .startDate(FORMATTER.parse("01/01/2005"))
                    .school("Université Hassan II de Casablanca")
                    .grade("Excellent")
                    .build()
    );
    List<SeleniumEducation> actualEducations = dataSeleniumExtractorService.getEducations("UserId");
    assertEquals(expectedEducations, actualEducations);
  }

  @Test
  public void testGetCertifications() throws IOException, ParseException {
    when(seleniumFetcherService.fetchCertifications(anyString()))
        .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-certifications.html"));
    List<SeleniumCertification> expectedCertifications = Arrays.asList(
              SeleniumCertification.builder()
                  .certificationName("Adobe Certified Master - Adobe Experience Manager Sites Architect")
                  .certificationProvider("Adobe")
                  .certifiedDate(FORMATTER.parse("01/06/2020"))
                  .build(),
              SeleniumCertification.builder()
                  .certificationName("Adobe Experience Manager 6 certified Architect")
                  .certificationProvider("Adobe")
                  .certifiedDate(FORMATTER.parse("01/10/2017"))
                  .build(),
              SeleniumCertification.builder()
                  .certificationName("AEM 6 Certified Business Practitioner")
                  .certificationProvider("Adobe France")
                  .certifiedDate(FORMATTER.parse("01/05/2017"))
                  .build(),
              SeleniumCertification.builder()
                  .certificationName("AEM 6 Certified Lead Developper")
                  .certificationProvider("Adobe France")
                  .certifiedDate(FORMATTER.parse("01/05/2017"))
                  .build(),
              SeleniumCertification.builder()
                  .certificationName("Adobe certified Expert : CQ Component Developer")
                  .certificationProvider("")
                  .build(),
             SeleniumCertification.builder()
                  .certificationName("Sun Certified Java Programmer")
                  .certificationProvider("")
                  .build()
    );
    List<SeleniumCertification> actualSeleniumCertifications = dataSeleniumExtractorService.getCertifications("UserId");
    assertEquals(expectedCertifications, actualSeleniumCertifications);
  }

  @Test
  public void testGetSkills() throws IOException {
    when(seleniumFetcherService.fetchSkills(anyString()))
        .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-skills.html"));
    List<SeleniumSkill> expectedSkills = new ArrayList<>();
    List<String> skillEndorsements1 = new ArrayList<>();
    skillEndorsements1.add("Compétences recommandées par 3 collègues chez Capgemini");
    expectedSkills.add(SeleniumSkill.builder()
        .skillName("J2EE Application Development")
        .skillEndorsements(skillEndorsements1)
        .build());
    List<String> skillEndorsements2 = new ArrayList<>();
    skillEndorsements2.add("Compétences recommandées par Abdelahad SATOUR et 1 autre personne très compétente dans ce domaine");
    skillEndorsements2.add("Compétences recommandées par 5 collègues chez Capgemini");
    expectedSkills.add(SeleniumSkill.builder()
        .skillName("Java Enterprise Edition")
        .skillEndorsements(skillEndorsements2)
        .build());
    List<String> skillEndorsements3 = new ArrayList<>();
    skillEndorsements3.add("Compétences recommandées par 3 collègues chez Capgemini");
    expectedSkills.add(SeleniumSkill.builder()
        .skillName("Jrules")
        .skillEndorsements(skillEndorsements3)
        .build());
    List<SeleniumSkill> actualSkills = dataSeleniumExtractorService.getSkills("UserId");
    assertEquals(expectedSkills, actualSkills);
  }

  @Test
  public void testGetLanguages() throws IOException {
    when(seleniumFetcherService.fetchLanguages(anyString()))
        .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-languages.html"));
    List<SeleniumLanguage> expectedLanguages = Arrays.asList(
                SeleniumLanguage.builder()
                    .languageName("Anglais")
                    .languageLevel("Full professional proficiency")
                    .build(),
                SeleniumLanguage.builder()
                    .languageName("Arabe")
                    .languageLevel("Native or bilingual proficiency")
                    .build(),
                SeleniumLanguage.builder()
                    .languageName("Espagnol")
                    .languageLevel("Elementary proficiency")
                    .build(),
                SeleniumLanguage.builder()
                    .languageName("Français")
                    .languageLevel("Native or bilingual proficiency")
                    .build()
        );

    List<SeleniumLanguage> actualLanguages = dataSeleniumExtractorService.getLanguages("UserId");
    assertEquals(expectedLanguages, actualLanguages);
  }


  @Test
  public void testGetExperiences() throws IOException, ParseException {
    when(seleniumFetcherService.fetchExperiences(anyString()))
        .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-experiences.html"));
    List<SeleniumExperience> expectedExperiences = Arrays.asList(
                SeleniumExperience.builder()
                    .company("Classcof · Stage")
                    .mission("- Échanger avec les clients pour définir la demande (marques de textile). - Organiser et faire le suivi de réalisation (transit, réception et production). - Respecter et suivre les processus opérationnels. - Veiller au respect des délais et de la qualité.")
                    .roleName("Responsable Relations Clients")
                    .startDate(FORMATTER.parse("01/10/2019"))
                    .endDate(FORMATTER.parse("01/01/2020"))
                    .location("Fès, Fès-Meknès, Maroc")
                    .build(),
                SeleniumExperience.builder()
                    .company("Institut Pascal · Stage")
                    .mission("Etude de l'influence de la machine sur les défauts produits sur une pièce pour en impression 3D: Ordonnancement et planning du travail. - Etude bibliographique. - Recherche des différents paramètres influant la qualité des pièces imprimés. - Réalisation d’expériences et mesures. - Expression graphique des différents résultats (CATIA V5, EXCEL) - Définition des températures et vitesses d’impression optimales pour une meilleure qualité.")
                    .roleName("Ingénieur Recherche")
                    .startDate(FORMATTER.parse("01/02/2019"))
                    .endDate(FORMATTER.parse("01/06/2019"))
                    .location("Région de Clermont-Ferrand, France")
                    .build(),
                SeleniumExperience.builder()
                    .company("RetroPark Casablanca · Stage")
                    .mission("Conception d’une scène mobile: - Ordonnancement des taches sur la période donnée pour le respect des délais (MS PROJECT). - Conception des pièces sous dans le respect du cahier des charges (CATIA V5, SOLIDWORKS). - Réalisation des plans de détails, des sous-ensembles et des ensembles. - Détermination des matériaux adaptés. - Effectuation des calculs, réalisation des simulations numériques (ANSYS WORKBENCH). - Contact des fournisseurs pour les différents devis nécessaires.")
                    .roleName("Ingénieur conception mécanique")
                    .startDate(FORMATTER.parse("01/02/2017"))
                    .endDate(FORMATTER.parse("01/07/2017"))
                    .location("Préfecture de Casablanca, Morocco")
                    .build()
    );

    List<SeleniumExperience> actualExperiences = dataSeleniumExtractorService.getExperiences("UserId");
    assertEquals(expectedExperiences, actualExperiences);
  }

  public String getCollection(String filePath) throws IOException {

    ClassPathResource resource = new ClassPathResource(filePath);
    return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

  }

}
