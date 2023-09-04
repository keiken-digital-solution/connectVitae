package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class LinkedInCompanyTest {
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testLinkedInCompanyJsonMapping() throws IOException {
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

    LinkedInCompany expectedLinkedInCompany = LinkedInCompany.builder()
        .name("SQLI")
        .universalName("sqli")
        .phone(new PhoneNumber("+33 (0)1 85 64 20 20"))
        .specialities(specialities)
        .staffCount("2830")
        .companyType(LinkedInCompany.CompanyType.builder()
            .localizedName("Privately Held")
            .build())
        .companyPageUrl("http://www.sqli.com")
        .description("Founded in 1990, SQLI Digital Experience is a European full-service digital company that defines, builds and grows the digital business value of international A-brands. \nTechnical and creative thinkers, their teams are committed to delivering meaningful and engaging experiences by leveraging technologies, methodologies, skills and creativity to get closer to the customer or user and capture their attention. They design, develop and deploy solid and high-performing architectures that improve business agility, increase efficiencies and facilitate business growth.\nTheir 2,100 employees are located in 13 countries: France, Switzerland, Luxembourg, Belgium, The United Kingdom, Germany, Sweden, The Netherlands, Denmark, Spain, Morocco, Mauritius and Dubai. In 2021, the SQLI Group achieved revenue of €226m.\n\nSQLI has been listed on Euronext Paris (SQI) since 21 July 2000. \n\nFor more information, visit our website: https://www.sqli.com ")
        .confirmedLocations(buildConfirmedLocations())
        .build();

    LinkedInCompany actualLinkedInCompany = objectMapper.readValue(linkedInCompanyAsJson, LinkedInCompany.class);

    assertEquals(expectedLinkedInCompany, actualLinkedInCompany);
  }

  @SuppressWarnings("checkstyle:MethodLength")
  private List<LinkedInCompanyLocation> buildConfirmedLocations() {
    return Arrays.asList(
        LinkedInCompanyLocation.builder()
            .country("FR")
            .geographicArea("Ile-de-France")
            .city("Levallois-Perret")
            .postalCode("92300")
            .description("SQLI Paris")
            .line1("166, Rue Jules Guesde")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("SE")
            .geographicArea("Västra Götaland County")
            .city("Göteborg")
            .postalCode("411 15")
            .description("SQLI Gothenburg")
            .line1("Kyrkogatan 26")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("CH")
            .geographicArea("Genève")
            .city("Carouge")
            .postalCode("CH-1227")
            .description("SQLI Genève")
            .line1("Route des jeunes 12")
<<<<<<< HEAD
<<<<<<< HEAD
=======
            .build(),
        LinkedInCompanyLocation.builder()
            .country("CH")
            .geographicArea("Vaud")
            .city("Lausanne")
            .postalCode("CH-1006")
            .description("SQLI Suisse")
            .line1("Avenue William-Fraisse 3")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("FR")
            .geographicArea("Rhône-Alpes")
            .city("Lyon cedex 09")
            .postalCode("69 258")
            .description("SQLI Lyon")
            .line1("1 place Verrazzano  CP 611")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("FR")
            .geographicArea("Occitanie")
            .city("Toulouse Cedex 3")
            .postalCode("31026")
            .description("SQLI Toulouse")
            .line1("6 impasse de Lisieux CS43133")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("FR")
            .geographicArea("Pays de la Loire")
            .city("Nantes")
            .postalCode("44000")
            .description("SQLI Nantes")
            .line1("1, Boulevard de Berlin")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("MA")
            .geographicArea("Rabat - Sale - Kenitra")
            .city("Rabat")
            .postalCode("10000")
            .description("SQLI Maroc")
            .line1("Avenue Annakhil")
            .line2("Hay Riad - Immeuble High Tech (Hall A)")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("GB")
            .geographicArea("England")
            .city("Whitechapel")
            .postalCode("E1 6NF")
            .description("SQLI UK")
            .line1("118 Commercial Street")
            .line2("Level 3")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("FR")
            .geographicArea("Nouvelle-Aquitaine")
            .city("Pessac")
            .postalCode("33600")
            .description("SQLI Bordeaux")
            .line1("10, Rue Thomas Edison")
            .line2("Parc AMPeRIS, Bâtiment Canopée")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("FR")
            .geographicArea("Normandie")
            .city("Le Grand-Quevilly")
            .postalCode("76120")
            .description("SQLI Rouen")
            .line1("45 Avenue Général Leclerc")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("BE")
            .geographicArea("Flemish Region")
            .city("Diegem")
            .postalCode("1831")
            .description("SQLI Belgium Diegem")
            .line1("Lambroekstraat 5c")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("BE")
            .geographicArea("Flemish Region")
            .city("Sint-Martens-Latem")
            .postalCode("9830")
            .description("SQLI Belgium Ghent")
            .line1("Kortrijksesteenweg 62")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("MA")
            .geographicArea("L'Oriental")
            .city("Oujda")
            .postalCode("60000")
            .description("SQLI Oujda")
            .line1("Boulevard Mohammed VI")
            .line2("Complexe Technologique SQLI")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("ES")
            .geographicArea("Catalonia")
            .city("San Justo Desvern")
            .postalCode("08960")
            .description("SQLI Barcelona")
            .line1("Carrer de la Constitució, 3")
            .line2("Piso 2 puerta 3")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("LU")
            .geographicArea("Luxembourg")
            .city("Strassen")
            .postalCode("L-8010")
            .description("SQLI Luxembourg")
            .line1("204, Route d'arlon")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("DE")
            .city("Berlin")
            .postalCode("10999")
            .description("SQLI Berlin")
            .line1("Oranienstraße 183")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("DE")
            .geographicArea("North Rhine-Westphalia")
            .city("Dortmund")
            .postalCode("44263")
            .description("SQLI Dortmund")
            .line1("Phoenixseestraße 20")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("ES")
            .city("Valencia")
            .postalCode("46004")
            .description("SQLI Valencia")
            .line1("Calle Conde de Salvatierra 7")
            .line2("Puerta 3 Piso 1")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("SE")
            .geographicArea("Stockholm County")
            .city("Stockholm")
            .postalCode("111 57")
            .description("SQLI Stockholm")
            .line1("Sergels torg 12")
            .line2("Våning 17")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("NL")
            .geographicArea("North Holland")
            .city("Amsterdam")
            .postalCode("1013 AA")
            .description("SQLI Amsterdam")
            .line1("De Ruijterkade 6h")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("NL")
            .geographicArea("North Brabant")
            .city("Eindhoven")
            .postalCode("5657EB")
            .description("SQLI Eindhoven")
            .line1("Luchthavenweg 18d")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("AE")
            .city("Dubai")
            .description("SQLI MENA")
            .line1("Office 118, The Loft Offices Media City")
            .build(),
        LinkedInCompanyLocation.builder()
            .country("MA")
            .city("Casablanca")
            .postalCode("20000")
            .description("SQLI Casablanca")
            .line1("Immeuble 5 Zénith")
            .line2("Quartier Sidi Maârouf")
>>>>>>> f7688e1 (refactor(LinkedIn): copany and school info refactor)
=======
>>>>>>> a8334cc (refactor(LinkedIn): refactoring info extractor branch tests)
            .build()
    );
  }

}






