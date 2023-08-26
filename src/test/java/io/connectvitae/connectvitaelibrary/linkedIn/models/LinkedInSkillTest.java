package io.connectvitae.connectvitaelibrary.linkedIn.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LinkedInSkillTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void linkedInSkillJsonMappingTest() throws IOException {
    String jsonFilePath = "linkedIn/linkedin-skill.json";
    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    String linkedInSkillAsJson =
        StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

    LinkedInSkill expectedLinkedInSkill = LinkedInSkill.builder().name("Data Engineering").build();
    LinkedInSkill actualLinkedInSkill =
        objectMapper.readValue(linkedInSkillAsJson, LinkedInSkill.class);
    assertEquals(expectedLinkedInSkill, actualLinkedInSkill);
  }
}
