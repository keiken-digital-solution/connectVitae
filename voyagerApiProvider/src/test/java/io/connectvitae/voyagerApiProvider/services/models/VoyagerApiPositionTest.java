package io.connectvitae.voyagerApiProvider.services.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiPosition;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiTimePeriod;
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
public class VoyagerApiPositionTest {
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-yyyy");
  @Autowired
  private ObjectMapper objectMapper;

  @SuppressWarnings("checkstyle:LineLength")
  @Test
  public void testVoyagerApiPositionJsonMapping() throws ParseException, IOException {
    String jsonFilePath = "linkedIn/voyagerApiProvider/elements/linkedin-position-element.json";

    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    String linkedInPositionAsJson =
        StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    VoyagerApiTimePeriod timePeriod =
        VoyagerApiTimePeriod.builder()
            .startDate(DATE_FORMAT.parse("05-2017"))
            .endDate(DATE_FORMAT.parse("12-2019"))
            .build();
    VoyagerApiPosition
        expectedVoyagerApiPosition =
        VoyagerApiPosition.builder()
            .title("Senior Python Developer at Cigna")
            .locationName("Hartford, Connecticut Area")
            .timePeriod(timePeriod)
            .description(
                "Type: Development from Scratch\nRole: Python Engineer (Cloud)\n\nTechnology Used: Python,Dask,Flask,React,MySql,PySpark,Docker,Go,AWS,Kubernetes,Microservices,GraphQL\n\nResponsibilities:\n\n-Migrated monolith applications to microservice-based services using AWS, Python, Go, and Flask.\n-Wrote extendible code using TDD approach and containerized apps using Docker.\n-Created production-grade POC.\n-Worked with various data pipelines using AirFlow, Dask Pandas, and Numpy.\n-Leveraged AWS lambda, AWS ECS, AWS Elasticache, and DynamoDB for various services.\n-Created high-performance, scalable RestFul APIs using Python(Flask) /AWS API Gateway for various enterprise applications.\n-Worked on Single Page Applications using React by consuming RESTFul APIs.")
            .companyName("CGI")
            .build();
    VoyagerApiPosition actualVoyagerApiPosition =
        objectMapper.readValue(linkedInPositionAsJson, VoyagerApiPosition.class);
    assertEquals(expectedVoyagerApiPosition, actualVoyagerApiPosition);
  }
}
