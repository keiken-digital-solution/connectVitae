package io.connectvitae.voyagerApiProvider.services.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiSkill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class VoyagerApiSkillTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Test

  public void testVoyagerApiSkillJsonMapping() throws IOException {
    String jsonFilePath = "linkedIn/voyagerApiProvider/elements/linkedin-skill-element.json";

    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    String linkedInSkillAsJson =
        StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

    VoyagerApiSkill expectedVoyagerApiSkill = VoyagerApiSkill.builder().name("Data Engineering").build();
    VoyagerApiSkill actualVoyagerApiSkill =
        objectMapper.readValue(linkedInSkillAsJson, VoyagerApiSkill.class);
    assertEquals(expectedVoyagerApiSkill, actualVoyagerApiSkill);
  }
}
