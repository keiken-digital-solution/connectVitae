package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInCertification;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInEducation;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInPosition;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInProfile;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInSkill;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInTimePeriod;
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
@SuppressWarnings("checkstyle:LineLength")
public class VoyagerApiExtractorServiceTest {
  private final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
  @MockBean
  private VoyagerApiFetcherService fetcherService;
  @Autowired
  private VoyagerApiExtractorService extractorService;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testGetExperiences() throws IOException, ParseException {
    PositionView positionView = objectMapper.readValue(getJson(
        "linkedIn/voyagerApiProvider/collections/linkedin-position-view.json"), PositionView.class);
    when(fetcherService.fetchExperiences(anyString())).thenReturn(positionView);
    List<LinkedInPosition> expectedExperiences = new ArrayList<>();
    expectedExperiences.add(LinkedInPosition.builder()
        .locationName("France")
        .companyName("Capgemini")
        .title("Solution architect managing")
        .timePeriod(LinkedInTimePeriod.builder()
            .startDate((FORMATTER.parse("01/10/2017")))
            .endDate(FORMATTER.parse("01/02/2021"))
            .build())
        .build());
    expectedExperiences.add(LinkedInPosition.builder()
        .locationName("Région de Paris, France")
        .companyName("AXA")
        .title("Architecte applications")
        .timePeriod(LinkedInTimePeriod.builder()
            .startDate((FORMATTER.parse("01/01/2015")))
            .endDate(FORMATTER.parse("01/10/2017"))
            .build())
        .build());
    List<LinkedInPosition> actualExperiences = extractorService.getExperiences("profileId");
    assertEquals(expectedExperiences, actualExperiences);
  }

  @Test
  public void testGetEducations() throws IOException, ParseException {
    EducationView educationView = objectMapper.readValue(getJson(
        "linkedIn/voyagerApiProvider/collections/linkedin-education-view.json"), EducationView.class);
    when(fetcherService.fetchEducations(anyString())).thenReturn(educationView);

    List<LinkedInEducation> expectedEducations = new ArrayList<>();
    expectedEducations.add(LinkedInEducation.builder()
        .fieldOfStudy("Network and System Administration/Administrator")
        .timePeriod(LinkedInTimePeriod.builder()
            .startDate(FORMATTER.parse("01/01/2007"))
            .endDate(FORMATTER.parse("01/01/2010"))
            .build())
        .schoolName("Institut national des postes et telecommunications")
        .build());
    expectedEducations.add(LinkedInEducation.builder()
        .degreeName("Associate's degree")
        .fieldOfStudy("software engineering")
        .timePeriod(LinkedInTimePeriod.builder()
            .startDate(FORMATTER.parse("01/01/2005"))
            .endDate(FORMATTER.parse("01/01/2007"))
            .build())
        .schoolName("Université Hassan II Aïn Chock de Casablanca")
        .grade("Excellent")
        .build());
    List<LinkedInEducation> actualEducations = extractorService.getEducations("profileId");
    assertEquals(expectedEducations, actualEducations);
  }

  @Test
  public void testGetSkills() throws IOException {
    SkillView skillView = objectMapper.readValue(getJson(
        "linkedIn/voyagerApiProvider/collections/linkedin-skill-view.json"), SkillView.class);
    when(fetcherService.fetchSkills(anyString())).thenReturn(skillView);
    List<LinkedInSkill> expectedSkills = new ArrayList<>();
    expectedSkills.add(LinkedInSkill.builder()
        .name("J2EE Application Development")
        .build());
    expectedSkills.add(LinkedInSkill.builder()
        .name("Java Enterprise Edition")
        .build());
    expectedSkills.add(LinkedInSkill.builder()
        .name("Spring")
        .build());

    List<LinkedInSkill> actualSkills = extractorService.getSkills("profileId");
    assertEquals(expectedSkills, actualSkills);
  }

  @Test
  public void testGetCertifications() throws IOException, ParseException {
    CertificationView certificationView = objectMapper.readValue(getJson(
        "linkedIn/voyagerApiProvider/collections/linkedin-certification-view.json"), CertificationView.class);
    when(fetcherService.fetchCertifications(anyString())).thenReturn(certificationView);
    List<LinkedInCertification> expectedCertifications = new ArrayList<>();
    expectedCertifications.add(LinkedInCertification.builder()
        .name("AEM 6 Certified Lead Developper")
        .authority("Adobe France")
        .timePeriod(LinkedInTimePeriod.builder()
            .startDate(FORMATTER.parse("01/05/2017"))
            .build())
        .build());
    expectedCertifications.add(LinkedInCertification.builder()
        .name("AEM 6 Certified Business Practitioner ")
        .authority("Adobe France")
        .timePeriod(LinkedInTimePeriod.builder()
            .startDate(FORMATTER.parse("01/05/2017"))
            .build())
        .build());
    expectedCertifications.add(LinkedInCertification.builder()
        .name("Adobe Certified Master - Adobe Experience Manager Sites Architect")
        .authority("Adobe")
        .timePeriod(LinkedInTimePeriod.builder()
            .startDate(FORMATTER.parse("01/06/2020"))
            .build())
        .build());
    List<LinkedInCertification> actualCertifications = extractorService.getCertifications("profileId");
    assertEquals(expectedCertifications, actualCertifications);
  }

  @Test
  public void testGetUser() throws IOException {
    LinkedInProfile linkedInProfile = objectMapper.readValue(getJson(
        "linkedIn/voyagerApiProvider/elements/linkedin-profile-element.json"), LinkedInProfile.class);
    when(fetcherService.fetchUser(anyString())).thenReturn(linkedInProfile);
    LinkedInProfile expectedUser = LinkedInProfile.builder()
        .geoLocationName("Parsippany, New Jersey")
        .summary("As a Seasoned Software Engineer with a comprehensive background in Python, Go, API, and Cloud technologies.I have spent the past 14 years working with global leaders such as JP Morgan Chase, American Express, 3M Company, Alaska Airlines, Cigna Healthcare, and ADP. Throughout my career, I have consistently demonstrated my ability to exceed organizational and personal goals, driving growth and expansion for my employers.\n\nCurrently, I am serving as a Principal Software Engineer for ADP, where I am responsible for developing cutting-edge and scalable applications using Python and a wide range of technologies including Flask, Django, Pandas, Dask, Airflow, Numpy, MySql, Kafka, PySpark, Docker, Kubernetes, and Go. I am also deeply involved in data engineering, architecture, coding, and the migration of existing applications to the Azure/AWS platforms, as part of strategic digitization projects that focus on cloud, machine learning and artificial intelligence and microservices.\n\nIn my role, I work closely with senior leadership, client representatives, SRE and DevOps teams to ensure the successful completion of projects. My colleagues describe me as a dynamic, self-driven, and down-to-earth engineer with a strong ability to deliver superior IT solutions. My passion for technology, attention to detail and ability to think strategically, make me a valuable asset to any team.\n\nYou can contact me via email at  ** brij.pydata@gmail.com ***")
        .firstName("Brij kishore")
        .lastName("Pandey")
        .headline("Principal Software Engineer @ADP | Python | AWS | Go | Big Data | Spark | Kafka | Data Engineering")
        .industryName("Information Technology & Services")
        .build();
    LinkedInProfile actualUser = extractorService.getUser("profileId");
    assertEquals(expectedUser, actualUser);
  }


  // --------------------------------------- Helpers --------------------------------------- \\
  private String getJson(String jsonFilePath) throws IOException {
    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
  }
}
