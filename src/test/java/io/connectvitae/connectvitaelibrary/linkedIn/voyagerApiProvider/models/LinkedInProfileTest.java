package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("checkstyle:LineLength")
@SpringBootTest
public class LinkedInProfileTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void LinkedInProfileJsonMappingTest() throws IOException {
    String jsonFilePath = "linkedIn/voyagerApiProvider/linkedin-profile.json";

    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    String linkedInProfileAsJson =
        StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    LinkedInProfile expectedLinkedInProfile =
        LinkedInProfile.builder()
            .industryName("Information Technology & Services")
            .summary(
                "As a Seasoned Software Engineer with a comprehensive background in Python, Go, API, and Cloud technologies.I have spent the past 14 years working with global leaders such as JP Morgan Chase, American Express, 3M Company, Alaska Airlines, Cigna Healthcare, and ADP. Throughout my career, I have consistently demonstrated my ability to exceed organizational and personal goals, driving growth and expansion for my employers.\n\nCurrently, I am serving as a Principal Software Engineer for ADP, where I am responsible for developing cutting-edge and scalable applications using Python and a wide range of technologies including Flask, Django, Pandas, Dask, Airflow, Numpy, MySql, Kafka, PySpark, Docker, Kubernetes, and Go. I am also deeply involved in data engineering, architecture, coding, and the migration of existing applications to the Azure/AWS platforms, as part of strategic digitization projects that focus on cloud, machine learning and artificial intelligence and microservices.\n\nIn my role, I work closely with senior leadership, client representatives, SRE and DevOps teams to ensure the successful completion of projects. My colleagues describe me as a dynamic, self-driven, and down-to-earth engineer with a strong ability to deliver superior IT solutions. My passion for technology, attention to detail and ability to think strategically, make me a valuable asset to any team.\n\nYou can contact me via email at  ** brij.pydata@gmail.com ***")
            .geoLocationName("Parsippany, New Jersey")
            .headline(
                "Principal Software Engineer @ADP | Python | AWS | Go | Big Data | Spark | Kafka | Data Engineering")
            .firstName("Brij kishore")
            .lastName("Pandey")
            .build();
    LinkedInProfile actualLinkedInProfile =
        objectMapper.readValue(linkedInProfileAsJson, LinkedInProfile.class);
    assertEquals(expectedLinkedInProfile, actualLinkedInProfile);
  }
}
