package io.connectvitae.connectvitaelibrary.providers.seleniumProvider.services;


import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.*;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
public class VoyagerApiSeleniumExtractorServiceTest {
    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    @MockBean
    private SeleniumFetcherService seleniumFetcherService;
    @Autowired
    private SeleniumExtractorService dataSeleniumExtractorService;


    @Test
    public void testFetchEducations() throws IOException, ParseException {
        when(seleniumFetcherService.fetchEducations(anyString()))
                .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-educations.html"));
        List<SeleniumEducation> expectedEducations = new ArrayList<>();
        expectedEducations.add(SeleniumEducation.builder()
                .degree("Network and System Administration/Administrator")
                .endDate(FORMATTER.parse("01/01/2010"))
                .startDate(FORMATTER.parse("01/01/2007"))
                .school("Institut national des postes et telecommunications")
                .build());
        expectedEducations.add(SeleniumEducation.builder()
                .degree("Associate's degree, software engineering")
                .endDate(FORMATTER.parse("01/01/2007"))
                .startDate(FORMATTER.parse("01/01/2005"))
                .school("Université Hassan II de Casablanca")
                .grade("Excellent")
                .build());
        List<SeleniumEducation> actualEducations = dataSeleniumExtractorService.getEducations("UserId");
        assertEquals(expectedEducations,actualEducations);


    }

    @Test
    public void testFetchCertifications() throws IOException, ParseException {
        when(seleniumFetcherService.fetchCertifications(anyString()))
                .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-certifications.html"));
        List<SeleniumCertification> expectedCertifications = new ArrayList<>();
        expectedCertifications.add(SeleniumCertification.builder()
                .certificationName("Adobe Certified Master - Adobe Experience Manager Sites Architect")
                .certificationProvider("Adobe")
                .certifiedDate(FORMATTER.parse("01/06/2020"))
                .build());
        expectedCertifications.add(SeleniumCertification.builder()
                .certificationName("Adobe Experience Manager 6 certified Architect")
                .certificationProvider("Adobe")
                .certifiedDate(FORMATTER.parse("01/10/2017"))
                .build());
        expectedCertifications.add(SeleniumCertification.builder()
                .certificationName("AEM 6 Certified Business Practitioner")
                .certificationProvider("Adobe France")
                .certifiedDate(FORMATTER.parse("01/05/2017"))
                .build());
        List<io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumCertification> actualSeleniumCertifications = dataSeleniumExtractorService.getCertifications("UserId");
        assertEquals(expectedCertifications, actualSeleniumCertifications);
    }

    @Test
    public void testFetchSkills() throws IOException {
        when(seleniumFetcherService.fetchSkills(anyString()))
                .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-skills.html"));
        List<SeleniumSkill> expectedSkills = new ArrayList<>();
        expectedSkills.add(SeleniumSkill.builder()
                .skillName("J2EE Application Development")
                .build());
        expectedSkills.add(SeleniumSkill.builder()
                .skillName("Java Enterprise Edition")
                .build());
        expectedSkills.add(SeleniumSkill.builder()
                .skillName("Jrules")
                .build());
        List<SeleniumSkill> actualSkills = dataSeleniumExtractorService.getSkills("UserId");
        assertEquals(expectedSkills,actualSkills);
    }

    @Test
    public void testFetchExperiences() throws IOException, ParseException {
        when(seleniumFetcherService.fetchExperiences(anyString()))
                .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-experiences.html"));
        List<SeleniumExperience> expectedExperiences = new ArrayList<>();
        expectedExperiences.add(SeleniumExperience.builder()
                .company("Classcof · Stage")
                .mission("- Échanger avec les clients pour définir la demande (marques de textile). - Organiser et faire le suivi de réalisation (transit, réception et production). - Respecter et suivre les processus opérationnels. - Veiller au respect des délais et de la qualité.")
                .roleName("Responsable Relations Clients")
                .startDate(FORMATTER.parse("01/10/2019"))
                .endDate(FORMATTER.parse("01/01/2020"))
                .location("Fès, Fès-Meknès, Maroc")
                .build());
        expectedExperiences.add(SeleniumExperience.builder()
                .company("Institut Pascal · Stage")
                .mission("Etude de l'influence de la machine sur les défauts produits sur une pièce pour en impression 3D: Ordonnancement et planning du travail. - Etude bibliographique. - Recherche des différents paramètres influant la qualité des pièces imprimés. - Réalisation d’expériences et mesures. - Expression graphique des différents résultats (CATIA V5, EXCEL) - Définition des températures et vitesses d’impression optimales pour une meilleure qualité.")
                .roleName("Ingénieur Recherche")
                .startDate(FORMATTER.parse("01/02/2019"))
                .endDate(FORMATTER.parse("01/06/2019"))
                .location("Région de Clermont-Ferrand, France")
                .build());
        expectedExperiences.add(SeleniumExperience.builder()
                .company("RetroPark Casablanca · Stage")
                .mission("Conception d’une scène mobile: - Ordonnancement des taches sur la période donnée pour le respect des délais (MS PROJECT). - Conception des pièces sous dans le respect du cahier des charges (CATIA V5, SOLIDWORKS). - Réalisation des plans de détails, des sous-ensembles et des ensembles. - Détermination des matériaux adaptés. - Effectuation des calculs, réalisation des simulations numériques (ANSYS WORKBENCH). - Contact des fournisseurs pour les différents devis nécessaires.")
                .roleName("Ingénieur conception mécanique")
                .startDate(FORMATTER.parse("01/02/2017"))
                .endDate(FORMATTER.parse("01/07/2017"))
                .location("Préfecture de Casablanca, Morocco")
                .build());

        List<SeleniumExperience> actualExperiences = dataSeleniumExtractorService.getExperiences("UserId");
        assertEquals(expectedExperiences, actualExperiences);
    }

    public String getCollection(String filePath) throws IOException {

        ClassPathResource resource = new ClassPathResource(filePath);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

    }

}
