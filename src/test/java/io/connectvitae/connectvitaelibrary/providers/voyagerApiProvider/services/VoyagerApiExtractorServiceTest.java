package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.connectvitae.connectvitaelibrary.models.*;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumCertification;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumExperience;
import io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models.SeleniumSkill;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInProfile;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.CertificationView;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.EducationView;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.PositionView;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views.SkillView;
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
public class VoyagerApiExtractorServiceTest {

    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    @MockBean
    VoyagerApiFetcherService fetcherService;
    @Autowired
    VoyagerApiExtractorService extractorService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetExperiences() throws IOException, ParseException {
        PositionView positionView =  objectMapper.readValue(getJson(
                "linkedIn/voyagerApiProvider/collections/linkedin-position-view.json"), PositionView.class);
        when(fetcherService.fetchExperiences(anyString())).thenReturn(positionView);
        List<Experience> expectedExperiences = new ArrayList<>();
        expectedExperiences.add(Experience.builder()
                .company("Capgemini")
                .roleName("Solution architect managing")
                .startDate(FORMATTER.parse("01/10/2017"))
                .endDate(FORMATTER.parse("01/02/2021"))
                .build());
        expectedExperiences.add(Experience.builder()
                .company("AXA")
                .roleName("Architecte applications")
                .startDate(FORMATTER.parse("01/01/2015"))
                .endDate(FORMATTER.parse("01/10/2017"))
                .build());
        List<Experience> actualExperiences = extractorService.getExperiences("profileId");
        assertEquals(expectedExperiences, actualExperiences);
    }

    @Test
    public void testGetEducations() throws IOException, ParseException {
        EducationView educationView =  objectMapper.readValue(getJson(
                "linkedIn/voyagerApiProvider/collections/linkedin-education-view.json"), EducationView.class);
        when(fetcherService.fetchEducations(anyString())).thenReturn(educationView);

        List<Education> expectedEducations = new ArrayList<>();
        expectedEducations.add(Education.builder()
                .specialty("Network and System Administration/Administrator")
                .endDate(FORMATTER.parse("01/01/2010"))
                .startDate(FORMATTER.parse("01/01/2007"))
                .school("Institut national des postes et telecommunications")
                .build());
        expectedEducations.add(Education.builder()
                .degree("Associate's degree")
                .specialty("software engineering")
                .endDate(FORMATTER.parse("01/01/2007"))
                .startDate(FORMATTER.parse("01/01/2005"))
                .school("Université Hassan II Aïn Chock de Casablanca")
                .build());
        List<Education> actualEducations = extractorService.getEducations("profileId");
        assertEquals(expectedEducations,actualEducations);
    }

    @Test
    public void testGetSkills() throws IOException {
        SkillView skillView =  objectMapper.readValue(getJson(
                "linkedIn/voyagerApiProvider/collections/linkedin-skill-view.json"), SkillView.class);
        when(fetcherService.fetchSkills(anyString())).thenReturn(skillView);
        List<Skill> expectedSkills = new ArrayList<>();
        expectedSkills.add(Skill.builder()
                .skillName("J2EE Application Development")
                .build());
        expectedSkills.add(Skill.builder()
                .skillName("Java Enterprise Edition")
                .build());
        expectedSkills.add(Skill.builder()
                .skillName("Spring")
                .build());

        List<Skill> actualSkills = extractorService.getSkills("profileId");
        assertEquals(expectedSkills, actualSkills);
    }

    @Test
    public void testGetCertifications() throws IOException, ParseException {
        CertificationView certificationView =  objectMapper.readValue(getJson(
                "linkedIn/voyagerApiProvider/collections/linkedin-certification-view.json"), CertificationView.class);
        when(fetcherService.fetchCertifications(anyString())).thenReturn(certificationView);
        List<Certification> expectedCertifications = new ArrayList<>();
        expectedCertifications.add(Certification.builder()
                .certificationName("AEM 6 Certified Lead Developper")
                .certificationProvider("Adobe France")
                .certifiedDate(FORMATTER.parse("01/05/2017"))
                .build());
        expectedCertifications.add(Certification.builder()
                .certificationName("AEM 6 Certified Business Practitioner ")
                .certificationProvider("Adobe France")
                .certifiedDate(FORMATTER.parse("01/15/2017"))
                .build());
        expectedCertifications.add(Certification.builder()
                .certificationName("Adobe Certified Master - Adobe Experience Manager Sites Architect")
                .certificationProvider("Adobe")
                .certifiedDate(FORMATTER.parse("01/06/2020"))
                .build());
        List<Certification> actualCertifications = extractorService.getCertifications("profileId");
    }

    @Test
    public void testGetUser() throws IOException {
        LinkedInProfile linkedInProfile =  objectMapper.readValue(getJson(
                "linkedIn/voyagerApiProvider/elements/linkedin-profile-element.json"), LinkedInProfile.class);
        when(fetcherService.fetchUser(anyString())).thenReturn(linkedInProfile);
        User expectedUser = User.builder()
                .address("Parsippany, New Jersey")
                .bio("As a Seasoned Software Engineer with a comprehensive background in Python, Go, API, and Cloud technologies.I have spent the past 14 years working with global leaders such as JP Morgan Chase, American Express, 3M Company, Alaska Airlines, Cigna Healthcare, and ADP. Throughout my career, I have consistently demonstrated my ability to exceed organizational and personal goals, driving growth and expansion for my employers.\n\nCurrently, I am serving as a Principal Software Engineer for ADP, where I am responsible for developing cutting-edge and scalable applications using Python and a wide range of technologies including Flask, Django, Pandas, Dask, Airflow, Numpy, MySql, Kafka, PySpark, Docker, Kubernetes, and Go. I am also deeply involved in data engineering, architecture, coding, and the migration of existing applications to the Azure/AWS platforms, as part of strategic digitization projects that focus on cloud, machine learning and artificial intelligence and microservices.\n\nIn my role, I work closely with senior leadership, client representatives, SRE and DevOps teams to ensure the successful completion of projects. My colleagues describe me as a dynamic, self-driven, and down-to-earth engineer with a strong ability to deliver superior IT solutions. My passion for technology, attention to detail and ability to think strategically, make me a valuable asset to any team.\n\nYou can contact me via email at  ** brij.pydata@gmail.com ***")
                .firstName("Brij kishore")
                .lastName("Pandey")
                .build();
        User actualUser = extractorService.getUser("profileId");
        assertEquals(expectedUser, actualUser);
    }


    // --------------------------------------- Helpers --------------------------------------- \\

    private String getJson(String jsonFilePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(jsonFilePath);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}