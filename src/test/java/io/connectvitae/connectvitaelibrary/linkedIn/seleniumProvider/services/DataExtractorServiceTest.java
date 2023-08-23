package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services;


import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Education;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.Skill;
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
public class DataExtractorServiceTest {
    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    @MockBean
    private SeleniumService seleniumService;
    @Autowired
    private DataExtractorService dataExtractorService;


    @Test
    public void testFetchEducations() throws IOException, ParseException {
        when(seleniumService.fetchEducations(anyString()))
                .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-educations.html"));
        List<Education> expectedEducations = new ArrayList<>();
        expectedEducations.add(Education.builder()
                .degree("Network and System Administration/Administrator")
                .endDate(FORMATTER.parse("01/01/2010"))
                .startDate(FORMATTER.parse("01/01/2007"))
                .school("Institut national des postes et telecommunications")
                .build());
        expectedEducations.add(Education.builder()
                .degree("Associate's degree, software engineering")
                .endDate(FORMATTER.parse("01/01/2007"))
                .startDate(FORMATTER.parse("01/01/2005"))
                .school("Université Hassan II de Casablanca")
                .grade("Excellent")
                .build());
        List<Education> actualEducations = dataExtractorService.getEducations("UserId");
        assertEquals(expectedEducations,actualEducations);


    }

    @Test
    public void testFetchCertifications() throws IOException, ParseException {
        when(seleniumService.fetchCertifications(anyString()))
                .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-certifications.html"));
        List<Certification> expectedCertifications = new ArrayList<>();
        expectedCertifications.add(Certification.builder()
                .certificationName("Adobe Certified Master - Adobe Experience Manager Sites Architect")
                .certificationProvider("Adobe")
                .certifiedDate(FORMATTER.parse("01/06/2020"))
                .build());
        expectedCertifications.add(Certification.builder()
                .certificationName("Adobe Experience Manager 6 certified Architect")
                .certificationProvider("Adobe")
                .certifiedDate(FORMATTER.parse("01/10/2017"))
                .build());
        expectedCertifications.add(Certification.builder()
                .certificationName("AEM 6 Certified Business Practitioner")
                .certificationProvider("Adobe France")
                .certifiedDate(FORMATTER.parse("01/05/2017"))
                .build());
        List<Certification> actualCertifications = dataExtractorService.getCertifications("UserId");
        assertEquals(expectedCertifications,actualCertifications);
    }

    @Test
    public void testFetchSkills() throws IOException {
        when(seleniumService.fetchSkills(anyString()))
                .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-skills.html"));
        List<Skill> expectedSkills = new ArrayList<>();
        expectedSkills.add(Skill.builder()
                .skillName("J2EE Application Development")
                .build());
        expectedSkills.add(Skill.builder()
                .skillName("Java Enterprise Edition")
                .build());
        expectedSkills.add(Skill.builder()
                .skillName("Jrules")
                .build());
        List<Skill> actualSkills = dataExtractorService.getSkills("UserId");
        assertEquals(expectedSkills,actualSkills);
    }

    @Test
    public void testFetchExperiences() throws IOException, ParseException {
        when(seleniumService.fetchExperiences(anyString()))
                .thenReturn(getCollection("linkedIn/seleniumProvider/collections/linkedin-experiences.html"));
        List<Experience> expectedExperiences = new ArrayList<>();
        expectedExperiences.add(Experience.builder()
                .company("Classcof · Stage")
                .mission("- Échanger avec les clients pour définir la demande (marques de textile). - Organiser et faire le suivi de réalisation (transit, réception et production). - Respecter et suivre les processus opérationnels. - Veiller au respect des délais et de la qualité.")
                .roleName("Responsable Relations Clients")
                .startDate(FORMATTER.parse("01/10/2019"))
                .endDate(FORMATTER.parse("01/01/2020"))
                .location("Fès, Fès-Meknès, Maroc")
                .build());
        expectedExperiences.add(Experience.builder()
                .company("Institut Pascal · Stage")
                .mission("Etude de l'influence de la machine sur les défauts produits sur une pièce pour en impression 3D: Ordonnancement et planning du travail. - Etude bibliographique. - Recherche des différents paramètres influant la qualité des pièces imprimés. - Réalisation d’expériences et mesures. - Expression graphique des différents résultats (CATIA V5, EXCEL) - Définition des températures et vitesses d’impression optimales pour une meilleure qualité.")
                .roleName("Ingénieur Recherche")
                .startDate(FORMATTER.parse("01/02/2019"))
                .endDate(FORMATTER.parse("01/06/2019"))
                .location("Région de Clermont-Ferrand, France")
                .build());
        expectedExperiences.add(Experience.builder()
                .company("RetroPark Casablanca · Stage")
                .mission("Conception d’une scène mobile: - Ordonnancement des taches sur la période donnée pour le respect des délais (MS PROJECT). - Conception des pièces sous dans le respect du cahier des charges (CATIA V5, SOLIDWORKS). - Réalisation des plans de détails, des sous-ensembles et des ensembles. - Détermination des matériaux adaptés. - Effectuation des calculs, réalisation des simulations numériques (ANSYS WORKBENCH). - Contact des fournisseurs pour les différents devis nécessaires.")
                .roleName("Ingénieur conception mécanique")
                .startDate(FORMATTER.parse("01/02/2017"))
                .endDate(FORMATTER.parse("01/07/2017"))
                .location("Préfecture de Casablanca, Morocco")
                .build());

        List<Experience> actualExperiences = dataExtractorService.getExperiences("UserId");
        assertEquals(expectedExperiences, actualExperiences);
    }

    public String getCollection(String filePath) throws IOException {

        ClassPathResource resource = new ClassPathResource(filePath);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

    }

}
