package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.services;


import io.connectvitae.connectvitaelibrary.mappers.VoyagerApiMapperService;
import io.connectvitae.connectvitaelibrary.models.*;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

public class VoyagerApiMapperServiceTest {
  @Autowired
  private VoyagerApiMapperService voyagerApiMapperService;

  @Test
  void testMapExperience() {
    LinkedInPosition linkedInPosition =
        LinkedInPosition.builder()
            .companyName("Keiken Digital")
            .description("Working on developing a intern project for skills management")
            .locationName("Paris, France")
            .title("Software Developer")
            .build();
    Experience actualExperience = voyagerApiMapperService.mapExperience(linkedInPosition);

    Experience expectedExperience =
        Experience.builder()
            .company("Keiken Digital")
            .mission("Working on developing a intern project for skills management")
            .roleName("Software Developer")
            .build();

    assertEquals(expectedExperience, actualExperience);
  }

  @Test
  void testMapCertification() {
    LinkedInCertification linkedInCertification =
        LinkedInCertification.builder()
            .authority("Adobe")
            .name("Adobe certified Expert : CQ Component Developer")
            .build();
    Certification expectedCertification =
        Certification.builder()
            .certificationProvider("Adobe")
            .certificationName("Adobe certified Expert : CQ Component Developer")
            .build();
    Certification actualCertification = voyagerApiMapperService.mapCertification(linkedInCertification);
    assertEquals(expectedCertification, actualCertification);
  }

  @Test
  void testMapTraining() {
    LinkedInEducation linkedInEducation =
        LinkedInEducation.builder()
            .degreeName("Engineer")
            .fieldOfStudy("Information Technology")
            .schoolName("INPT")
            .build();
    Education expectedEducation =
        Education.builder()
            .school("INPT")
            .specialty("Information Technology")
            .degree("Engineer")
            .build();
    Education actualEducation = voyagerApiMapperService.mapEducation(linkedInEducation);
    assertEquals(expectedEducation, actualEducation);
  }




  @Test
  void testMapSkill() {
    LinkedInSkill linkedInSkill = LinkedInSkill.builder().name("Project Management").build();
    Skill expectedSkill = Skill.builder().skillName("Project Management").build();
    Skill actualSkill = voyagerApiMapperService.mapSkill(linkedInSkill);
    assertEquals(expectedSkill, actualSkill);
  }


  @Test
  void testMapUser() {
    LinkedInProfile linkedInProfile =
        LinkedInProfile.builder()
            .firstName("John")
            .lastName("Lenon")
            .headline("Software Engineer")
            .geoLocationName("Paris, France")
            .summary(
                "An enthusiastic software engineer who is interested in artificial intelligence")
            .industryName("Information technology")
            .build();
    User expectedUser =
        User.builder()
            .firstName("John")
            .lastName("Lenon")
            .address("Paris, France")
            .bio("An enthusiastic software engineer who is interested in artificial intelligence")
            .build();
    User actualUser = voyagerApiMapperService.mapUser(linkedInProfile);
    assertEquals(expectedUser, actualUser);
  }

  @Test
  void testConvertSchool() {
    LinkedInSchool linkedInSchool =
        LinkedInSchool.builder()
            .basicSchoolInfo(
                LinkedInSchool.BasicSchoolInfo.builder()
                    .miniSchool(
                        LinkedInSchool.MiniSchool.builder()
                            .schoolName("Dubai British School")
                            .build())
                    .build()
            )
            .schoolType("Private")
            .phoneNumber(new PhoneNumber("+971 (0)4 361 9361)"))
            .address("Springs 3، Emirates Hills (Behind Spinneys/Springs Town Centre), Dubai, Dubai, ae").homepageUrl("http://www.dubaibritishschool.ae/")
            .description("Dubai British School Emirates Hills, a Taaleem School, is a dynamic British International School that aims to be at the leading edge of educational innovation and excellence in British international education globally. We provide an international curriculum firmly rooted in the National Curriculum of England and enriched by specialist provision for the Arabic language and a strong performing and visual arts programme, as well as a multitude of sporting and extra-curricular activities and opportunities.\n\nDubai British School prides itself on its inclusive approach to education. We do not discriminate on the grounds of race, nationality, disability or cultural background. We see the diversity of our student and staff population as one of our greatest assets, and we make every attempt to give each and every student full access to our learning programmes.\n\nTeachers are assisted by specialist staff, such as our Extended Learning Co-ordinator, in the identification of and provision for an individual student’s specific or exceptional learning needs. If a student should need additional learning support beyond that available at the school, then the school reserves the right to pass on to parents part or all of the additional cost incurred. However, we would discourage the use of private tutors by parents because we believe that we should be able to meet every student’s learning needs in full and to ensure that each and every student makes good progress in every respect within our stated curriculum.")
            .build();

    School expectedSchool =
        School.builder()
            .schoolName("Dubai British School")
            .schoolType("Private")
            .schoolNumber(("+971 (0)4 361 9361)"))
            .schoolAddress("Springs 3، Emirates Hills (Behind Spinneys/Springs Town Centre), Dubai, Dubai, ae")
            .schoolUrl("http://www.dubaibritishschool.ae/")
            .schoolDescription("Dubai British School Emirates Hills, a Taaleem School, is a dynamic British International School that aims to be at the leading edge of educational innovation and excellence in British international education globally. We provide an international curriculum firmly rooted in the National Curriculum of England and enriched by specialist provision for the Arabic language and a strong performing and visual arts programme, as well as a multitude of sporting and extra-curricular activities and opportunities.\n\nDubai British School prides itself on its inclusive approach to education. We do not discriminate on the grounds of race, nationality, disability or cultural background. We see the diversity of our student and staff population as one of our greatest assets, and we make every attempt to give each and every student full access to our learning programmes.\n\nTeachers are assisted by specialist staff, such as our Extended Learning Co-ordinator, in the identification of and provision for an individual student’s specific or exceptional learning needs. If a student should need additional learning support beyond that available at the school, then the school reserves the right to pass on to parents part or all of the additional cost incurred. However, we would discourage the use of private tutors by parents because we believe that we should be able to meet every student’s learning needs in full and to ensure that each and every student makes good progress in every respect within our stated curriculum.")
            .build();

    School actualSchool = voyagerApiMapperService.mapSchool(linkedInSchool);
    assertEquals(expectedSchool, actualSchool);
  }

  @Test
  public void testMapCompany() {
    LinkedInCompany linkedInCompany =
        LinkedInCompany.builder()
            .name("SQLI")
            .companyPageUrl("http://www.sqli.com")
            .universalName("sqli")
            .phone(new PhoneNumber("+33 (0)1 85 64 20 20"))
            .specialities(buildSpecialities())
            .description("Founded in 1990, SQLI Digital Experience is a European full-service digital company that defines, builds and grows the digital business value of international A-brands. \\nTechnical and creative thinkers, their teams are committed to delivering meaningful and engaging experiences by leveraging technologies, methodologies, skills and creativity to get closer to the customer or user and capture their attention. They design, develop and deploy solid and high-performing architectures that improve business agility, increase efficiencies and facilitate business growth.\\nTheir 2,100 employees are located in 13 countries: France, Switzerland, Luxembourg, Belgium, The United Kingdom, Germany, Sweden, The Netherlands, Denmark, Spain, Morocco, Mauritius and Dubai. In 2021, the SQLI Group achieved revenue of €226m.\\n\\nSQLI has been listed on Euronext Paris (SQI) since 21 July 2000. \\n\\nFor more information, visit our website: https://www.sqli.com \",")
            .confirmedLocations(buildConfirmedLocations())
            .build();
    Company actualCompany = voyagerApiMapperService.mapCompany(linkedInCompany);

    Company expectedCompany = Company.builder()
        .companyName("SQLI")
        .companyWebSiteUrl("http://www.sqli.com")
        .companyPhoneNumber("+33 (0)1 85 64 20 20")
        .companySpecialities(buildSpecialities())
        .companyDescription("Founded in 1990, SQLI Digital Experience is a European full-service digital company that defines, builds and grows the digital business value of international A-brands. \\nTechnical and creative thinkers, their teams are committed to delivering meaningful and engaging experiences by leveraging technologies, methodologies, skills and creativity to get closer to the customer or user and capture their attention. They design, develop and deploy solid and high-performing architectures that improve business agility, increase efficiencies and facilitate business growth.\\nTheir 2,100 employees are located in 13 countries: France, Switzerland, Luxembourg, Belgium, The United Kingdom, Germany, Sweden, The Netherlands, Denmark, Spain, Morocco, Mauritius and Dubai. In 2021, the SQLI Group achieved revenue of €226m.\\n\\nSQLI has been listed on Euronext Paris (SQI) since 21 July 2000. \\n\\nFor more information, visit our website: https://www.sqli.com \",")
        .companyLocations(buildConfirmedLocations())
        .build();

    assertEquals(expectedCompany, actualCompany);
  }

  // --------------------------------------- Helpers --------------------------------------- \\

  private List<String> buildSpecialities() {
    return Arrays.asList("SQLI Institut", "Aston Ecole", "Mobility", "Agilisation", "CRM", "Chatbot",
        "eCommerce", "Production Information Management", "Digital Experience",
        "Unified Commerce", "Experience Platform", "Digital Strategy", "UX", "UI",
        "Ergonomics", "Customer Journey", "Design Sprint", "Digital Marketing",
        "Data & Analytics", "Product Management", "Consulting", "Development", "Digital");
  }

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
            .build()
    );
  }
}
