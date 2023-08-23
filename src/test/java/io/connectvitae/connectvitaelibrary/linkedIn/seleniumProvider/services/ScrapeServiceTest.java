package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services;

import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Education;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.Skill;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ScrapeServiceTest {
    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    ScrapeService scrapeService;

    @Test
    public void testScrapeExperience() throws IOException, ParseException {
        Element linkedInExperienceElement =
                getElement("linkedIn/seleniumProvider/elements/linkedin-experience-element.html");


        Experience expectedExperience = Experience.builder()
                .startDate(FORMATTER.parse("01/06/2016"))
                .endDate(FORMATTER.parse("01/08/2016"))
                .company("Société de Fabrication des Cuisinières (SOFACUIS) . Stage")
                .roleName("Concepteur mécanique")
                .mission("Conception d'un model de four avec de nouvelles dimensions: - Etude des étapes de production des fours et cuisinières. - Conception des pièces (CATIA V5, SOLIDWORKS). - Validation de la conception avec le responsable.")
                .location("Fes -Maroc")
                .build();

        Experience actualExperience = scrapeService.scrapeExperience(linkedInExperienceElement);

        assertEquals(expectedExperience, actualExperience);
    }

    @Test
    public void testScrapeSkill() throws IOException {
        Element linkedInSkillElement =
                getElement("linkedIn/seleniumProvider/elements/linkedin-skill-element.html");

        Skill expectedSkill = Skill.builder()
                .skillName("Java Enterprise Edition")
                .build();

        Skill actualSkill = scrapeService.scrapeSkill(linkedInSkillElement);

        assertEquals(expectedSkill, actualSkill);
    }

    @Test
    public void testScrapeCertification() throws IOException, ParseException {
        Element linkedInCertificationElement =
                getElement("linkedIn/seleniumProvider/elements/linkedin-certification-element.html");
        Certification expectedCertification = Certification.builder()
                .certificationName("Adobe Certified Master - Adobe Experience Manager Sites Architect")
                .certificationProvider("Adobe")
                .certifiedDate(FORMATTER.parse("01/06/2020"))
                .build();
        Certification actualCertification = scrapeService.scrapeCertification(linkedInCertificationElement);
        assertEquals(expectedCertification,actualCertification);
    }

    @Test
    public void testScrapeEducation() throws IOException, ParseException {
        Element linkedInEducationElement =
                getElement("linkedIn/seleniumProvider/elements/linkedin-education-element.html");

        Education expectedEducation = Education.builder()
                .school("Université Hassan II de Casablanca")
                .degree("Associate's degree, software engineering")
                .startDate(FORMATTER.parse("01/01/2005"))
                .endDate(FORMATTER.parse("01/01/2007"))
                .grade("Excellent")
                .build();
        Education actualEducation = scrapeService.scrapeEducation(linkedInEducationElement);
        assertEquals(expectedEducation, actualEducation);
    }

    @Test
    public void testScrapeExperienceGroup() throws IOException, ParseException {
        Element linkedInExperienceGroupElement =
                getElement("linkedIn/seleniumProvider/elements/linkedin-experience-group-element.html");

        List<Experience> expectedExperienceGroup = new ArrayList<>();

        Experience firstExperience = Experience.builder()
                .startDate(FORMATTER.parse("01/11/2015"))
                .endDate(FORMATTER.parse("01/03/2016"))
                .company("Google")
                .roleName("Senior Research Scientist")
                .mission("As a member of the Google Brain team, I work on deep learning, both in terms of basic research and in terms of improving products. I'm writing a textbook on deep learning along with my PhD thesis advisors: www.deeplearningbook.org")
                .location("")
                .build();

        Experience secondExperience = Experience.builder()
                .startDate(FORMATTER.parse("01/07/2014"))
                .endDate(FORMATTER.parse("01/11/2015"))
                .company("Google")
                .roleName("Research Scientist")
                .mission("During my first year and change at Google, I contributed to TensorFlow, designed a new method for generating adversarial examples and using them to improve neural networks, made a bunch of visualizations showing that neural network loss functions aren't full of scary obstacles after all, supervised intern Tianqi Chen as he developed a method for rapidly transferring knowledge between neural networks, and did many other fun deep learning-related things.")
                .location("Mountain View, CA")
                .build();

        expectedExperienceGroup.add(firstExperience);
        expectedExperienceGroup.add(secondExperience);

        List<Experience> actualExperiences = scrapeService.scrapeExperiencesGroup(linkedInExperienceGroupElement);
        assertEquals(expectedExperienceGroup,actualExperiences);
    }
    public Element getElement(String filePath) throws IOException {

        ClassPathResource resource = new ClassPathResource(filePath);
        String elementAsText =
                StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        return Jsoup.parse(elementAsText,"UTF-8");


    }
}
