package io.connectvitae.voyagerApiProvider.services.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.connectvitae.voyagerApiProvider.models.PhoneNumber;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiCompany;
import io.connectvitae.voyagerApiProvider.models.VoyagerApiCompanyLocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SuppressWarnings("checkstyle:LineLength")
public class VoyagerApiCompanyTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testVoyagerApiCompanyJsonMapping() throws IOException {
    String jsonFilePath = "linkedIn/voyagerApiProvider/elements/linkedin-company-element.json";
    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    String linkedInCompanyAsJson = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

    List<String> specialities = Arrays.asList(
        "SQLI Institut", "Aston Ecole", "Mobility", "Agilisation", "CRM", "Chatbot",
        "eCommerce", "Production Information Management", "Digital Experience",
        "Unified Commerce", "Experience Platform", "Digital Strategy", "UX", "UI",
        "Ergonomics", "Customer Journey", "Design Sprint", "Digital Marketing",
        "Data & Analytics", "Product Management", "Consulting", "Development", "Digital"
    );

    VoyagerApiCompany expectedVoyagerApiCompany = VoyagerApiCompany.builder()
        .name("SQLI")
        .universalName("sqli")
        .phone(new PhoneNumber("+33 (0)1 85 64 20 20"))
        .specialities(specialities)
        .staffCount("2830")
        .companyType(VoyagerApiCompany.CompanyType.builder()
            .localizedName("Privately Held")
            .build())
        .companyPageUrl("http://www.sqli.com")
        .description("Founded in 1990, SQLI Digital Experience is a European full-service digital company that defines, builds and grows the digital business value of international A-brands. \nTechnical and creative thinkers, their teams are committed to delivering meaningful and engaging experiences by leveraging technologies, methodologies, skills and creativity to get closer to the customer or user and capture their attention. They design, develop and deploy solid and high-performing architectures that improve business agility, increase efficiencies and facilitate business growth.\nTheir 2,100 employees are located in 13 countries: France, Switzerland, Luxembourg, Belgium, The United Kingdom, Germany, Sweden, The Netherlands, Denmark, Spain, Morocco, Mauritius and Dubai. In 2021, the SQLI Group achieved revenue of €226m.\n\nSQLI has been listed on Euronext Paris (SQI) since 21 July 2000. \n\nFor more information, visit our website: https://www.sqli.com ")
        .confirmedLocations(buildConfirmedLocations())
        .build();

    VoyagerApiCompany actualVoyagerApiCompany = objectMapper.readValue(linkedInCompanyAsJson, VoyagerApiCompany.class);

    assertEquals(expectedVoyagerApiCompany, actualVoyagerApiCompany);
  }

  @SuppressWarnings("checkstyle:MethodLength")
  private List<VoyagerApiCompanyLocation> buildConfirmedLocations() {
    return Arrays.asList(
        VoyagerApiCompanyLocation.builder()
            .country("FR")
            .geographicArea("Ile-de-France")
            .city("Levallois-Perret")
            .postalCode("92300")
            .description("SQLI Paris")
            .line1("166, Rue Jules Guesde")
            .build(),
        VoyagerApiCompanyLocation.builder()
            .country("SE")
            .geographicArea("Västra Götaland County")
            .city("Göteborg")
            .postalCode("411 15")
            .description("SQLI Gothenburg")
            .line1("Kyrkogatan 26")
            .build(),
        VoyagerApiCompanyLocation.builder()
            .country("CH")
            .geographicArea("Genève")
            .city("Carouge")
            .postalCode("CH-1227")
            .description("SQLI Genève")
            .line1("Route des jeunes 12")
            .build()
    );
  }

}






