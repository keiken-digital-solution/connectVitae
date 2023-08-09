package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models;

import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD:src/test/java/io/connectvitae/connectvitaelibrary/linkedIn/models/LinkedInCertificationTest.java
=======
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.LinkedInCertification;
import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.LinkedInCompany;
import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.LinkedInTimePeriod;
>>>>>>> f3cca68 (feat(LinkedIn) : Implementing first selenium authentication):src/test/java/io/connectvitae/connectvitaelibrary/linkedIn/voyagerApiProvider/models/LinkedInCertificationTest.java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LinkedInCertificationTest {
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-yyyy");

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void linkedInCertificationJsonMappingTest() throws ParseException, IOException {
    String jsonFilePath = "linkedIn/linkedin-certification.json";
    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    String linkedInCertificationAsJson =
        StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    LinkedInTimePeriod timePeriod =
        LinkedInTimePeriod.builder().startDate(DATE_FORMAT.parse("05-2017")).build();
    LinkedInCompany company =
        LinkedInCompany.builder().name("Adobe France").universalName("adobe-france").build();
    LinkedInCertification expectedLinkedInCertification =
        LinkedInCertification.builder()
            .name("AEM 6 Certified Lead Developer")
            .authority("Adobe France")
            .timePeriod(timePeriod)
            .company(company)
            .build();
    LinkedInCertification actualLinkedInCertification =
        objectMapper.readValue(linkedInCertificationAsJson, LinkedInCertification.class);
    assertEquals(expectedLinkedInCertification, actualLinkedInCertification);
  }
}
