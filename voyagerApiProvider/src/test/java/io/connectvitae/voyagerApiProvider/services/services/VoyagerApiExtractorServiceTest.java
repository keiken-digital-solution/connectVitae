package io.connectvitae.voyagerApiProvider.services.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiCertification;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiEducation;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiPosition;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiProfile;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiSkill;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiTimePeriod;
import io.connectvitae.voyagerApiProvider.models.views.CertificationView;
import io.connectvitae.voyagerApiProvider.models.views.EducationView;
import io.connectvitae.voyagerApiProvider.models.views.PositionView;
import io.connectvitae.voyagerApiProvider.models.views.SkillView;
import io.connectvitae.voyagerApiProvider.services.VoyagerApiExtractorService;
import io.connectvitae.voyagerApiProvider.services.VoyagerApiFetcherService;
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
  private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
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
    List<VoyagerApiPosition> expectedExperiences = new ArrayList<>();
    expectedExperiences.add(VoyagerApiPosition.builder()
        .locationName("France")
        .companyName("Capgemini")
        .title("Solution architect managing")
        .timePeriod(VoyagerApiTimePeriod.builder()
            .startDate((FORMATTER.parse("01/10/2017")))
            .endDate(FORMATTER.parse("01/02/2021"))
            .build())
        .build());
    expectedExperiences.add(VoyagerApiPosition.builder()
        .locationName("Région de Paris, France")
        .companyName("AXA")
        .title("Architecte applications")
        .timePeriod(VoyagerApiTimePeriod.builder()
            .startDate((FORMATTER.parse("01/01/2015")))
            .endDate(FORMATTER.parse("01/10/2017"))
            .build())
        .build());
    List<VoyagerApiPosition> actualExperiences = extractorService.getExperiences("profileId");
    assertEquals(expectedExperiences, actualExperiences);
  }

  @Test
  public void testGetEducations() throws IOException, ParseException {
    EducationView educationView = objectMapper.readValue(getJson(
        "linkedIn/voyagerApiProvider/collections/linkedin-education-view.json"), EducationView.class);
    when(fetcherService.fetchEducations(anyString())).thenReturn(educationView);

    List<VoyagerApiEducation> expectedEducations = new ArrayList<>();
    expectedEducations.add(VoyagerApiEducation.builder()
        .fieldOfStudy("Network and System Administration/Administrator")
        .timePeriod(VoyagerApiTimePeriod.builder()
            .startDate(FORMATTER.parse("01/01/2007"))
            .endDate(FORMATTER.parse("01/01/2010"))
            .build())
        .schoolName("Institut national des postes et telecommunications")
        .build());
    expectedEducations.add(VoyagerApiEducation.builder()
        .degreeName("Associate's degree")
        .fieldOfStudy("software engineering")
        .timePeriod(VoyagerApiTimePeriod.builder()
            .startDate(FORMATTER.parse("01/01/2005"))
            .endDate(FORMATTER.parse("01/01/2007"))
            .build())
        .schoolName("Université Hassan II Aïn Chock de Casablanca")
        .grade("Excellent")
        .build());
    List<VoyagerApiEducation> actualEducations = extractorService.getEducations("profileId");
    assertEquals(expectedEducations, actualEducations);
  }

  @Test
  public void testGetSkills() throws IOException {
    SkillView skillView = objectMapper.readValue(getJson(
        "linkedIn/voyagerApiProvider/collections/linkedin-skill-view.json"), SkillView.class);
    when(fetcherService.fetchSkills(anyString())).thenReturn(skillView);
    List<VoyagerApiSkill> expectedSkills = new ArrayList<>();
    expectedSkills.add(VoyagerApiSkill.builder()
        .name("J2EE Application Development")
        .build());
    expectedSkills.add(VoyagerApiSkill.builder()
        .name("Java Enterprise Edition")
        .build());
    expectedSkills.add(VoyagerApiSkill.builder()
        .name("Spring")
        .build());

    List<VoyagerApiSkill> actualSkills = extractorService.getSkills("profileId");
    assertEquals(expectedSkills, actualSkills);
  }

  @Test
  public void testGetCertifications() throws IOException, ParseException {
    CertificationView certificationView = objectMapper.readValue(getJson(
        "linkedIn/voyagerApiProvider/collections/linkedin-certification-view.json"), CertificationView.class);
    when(fetcherService.fetchCertifications(anyString())).thenReturn(certificationView);
    List<VoyagerApiCertification> expectedCertifications = new ArrayList<>();
    expectedCertifications.add(VoyagerApiCertification.builder()
        .name("AEM 6 Certified Lead Developper")
        .authority("Adobe France")
        .timePeriod(VoyagerApiTimePeriod.builder()
            .startDate(FORMATTER.parse("01/05/2017"))
            .build())
        .build());
    expectedCertifications.add(VoyagerApiCertification.builder()
        .name("AEM 6 Certified Business Practitioner ")
        .authority("Adobe France")
        .timePeriod(VoyagerApiTimePeriod.builder()
            .startDate(FORMATTER.parse("01/05/2017"))
            .build())
        .build());
    expectedCertifications.add(VoyagerApiCertification.builder()
        .name("Adobe Certified Master - Adobe Experience Manager Sites Architect")
        .authority("Adobe")
        .timePeriod(VoyagerApiTimePeriod.builder()
            .startDate(FORMATTER.parse("01/06/2020"))
            .build())
        .build());
    List<VoyagerApiCertification> actualCertifications = extractorService.getCertifications("profileId");
    assertEquals(expectedCertifications, actualCertifications);
  }

  @Test
  public void testGetUser() throws IOException {
    VoyagerApiProfile voyagerApiProfile = objectMapper.readValue(getJson(
        "linkedIn/voyagerApiProvider/elements/linkedin-profile-element.json"), VoyagerApiProfile.class);
    when(fetcherService.fetchUser(anyString())).thenReturn(voyagerApiProfile);
    VoyagerApiProfile expectedUser = VoyagerApiProfile.builder()
        .geoLocationName("Parsippany, New Jersey")
        .summary("As a Seasoned Software Engineer with a comprehensive background in Python, Go, API, and Cloud technologies.I have spent the past 14 years working with global leaders such as JP Morgan Chase, American Express, 3M Company, Alaska Airlines, Cigna Healthcare, and ADP. Throughout my career, I have consistently demonstrated my ability to exceed organizational and personal goals, driving growth and expansion for my employers.\n\nCurrently, I am serving as a Principal Software Engineer for ADP, where I am responsible for developing cutting-edge and scalable applications using Python and a wide range of technologies including Flask, Django, Pandas, Dask, Airflow, Numpy, MySql, Kafka, PySpark, Docker, Kubernetes, and Go. I am also deeply involved in data engineering, architecture, coding, and the migration of existing applications to the Azure/AWS platforms, as part of strategic digitization projects that focus on cloud, machine learning and artificial intelligence and microservices.\n\nIn my role, I work closely with senior leadership, client representatives, SRE and DevOps teams to ensure the successful completion of projects. My colleagues describe me as a dynamic, self-driven, and down-to-earth engineer with a strong ability to deliver superior IT solutions. My passion for technology, attention to detail and ability to think strategically, make me a valuable asset to any team.\n\nYou can contact me via email at  ** brij.pydata@gmail.com ***")
        .firstName("Brij kishore")
        .lastName("Pandey")
        .headline("Principal Software Engineer @ADP | Python | AWS | Go | Big Data | Spark | Kafka | Data Engineering")
        .industryName("Information Technology & Services")
        .build();
    VoyagerApiProfile actualUser = extractorService.getUser("profileId");
    assertEquals(expectedUser, actualUser);
  }


  // --------------------------------------- Helpers --------------------------------------- \\
  private String getJson(String jsonFilePath) throws IOException {
    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
  }
}
