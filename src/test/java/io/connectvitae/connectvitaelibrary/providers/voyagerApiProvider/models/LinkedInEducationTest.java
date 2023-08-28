package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LinkedInEducationTest {
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-yyyy");
  @Autowired
  private ObjectMapper objectMapper;

  @Test

  public void LinkedInEducationJsonMappingTest() throws ParseException, IOException {
    String jsonFilePath = "linkedIn/voyagerApiProvider/linkedin-education.json";

    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    String linkedInEducationAsJson =
        StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

    LinkedInTimePeriod timePeriod =
        LinkedInTimePeriod.builder()
            .startDate(DATE_FORMAT.parse("09-2007"))
            .endDate(DATE_FORMAT.parse("07-2010"))
            .build();
    LinkedInEducation expectedLinkedInEducation =
        LinkedInEducation.builder()
            .schoolName("SRM University")
            .fieldOfStudy("Electrical and Electronics Engineering")
            .degreeName("Bachelor of Technology (B.Tech.)")
            .grade("Excellent")
            .build();
    LinkedInEducation actualLinkedInEducation =
        objectMapper.readValue(linkedInEducationAsJson, LinkedInEducation.class);
    assertEquals(expectedLinkedInEducation, actualLinkedInEducation);
  }
}