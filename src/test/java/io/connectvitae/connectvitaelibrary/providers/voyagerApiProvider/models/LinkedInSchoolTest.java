<<<<<<< HEAD
<<<<<<< HEAD:src/test/java/io/connectvitae/connectvitaelibrary/providers/voyagerApiProvider/models/LinkedInSchoolTest.java
package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models;
=======
package io.connectvitae.connectvitaelibrary.linkedIn.models;
>>>>>>> e9bc661 (temporary Commit for infoExtractor):src/test/java/io/connectvitae/connectvitaelibrary/linkedIn/models/LinkedInSchoolTest.java
=======
package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models;
>>>>>>> f7688e1 (refactor(LinkedIn): copany and school info refactor)

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
public class LinkedInSchoolTest {
<<<<<<< HEAD
<<<<<<< HEAD:src/test/java/io/connectvitae/connectvitaelibrary/providers/voyagerApiProvider/models/LinkedInSchoolTest.java
=======
>>>>>>> f7688e1 (refactor(LinkedIn): copany and school info refactor)
  @Autowired
  private ObjectMapper objectMapper;
  @SuppressWarnings("checkstyle:LineLength")
  @Test
  public void testLinkedInSchoolJsonMapping() throws IOException {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> a8334cc (refactor(LinkedIn): refactoring info extractor branch tests)
    String jsonFilePath = "linkedIn/voyagerApiProvider/elements/linkedin-school-element.json";
    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    String linkedInSchoolAsJson =
            StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

    LinkedInSchool actualLinkedInSchool =
            objectMapper.readValue(linkedInSchoolAsJson, LinkedInSchool.class);


    LinkedInSchool expectedLinkedInSchool = LinkedInSchool.builder()
            .basicSchoolInfo(
                LinkedInSchool.BasicSchoolInfo.builder()
                    .miniSchool(
                        LinkedInSchool.MiniSchool.builder()
                                .active(true)
                                .schoolName("Dubai British School")
                                .build())
                    .build()
                )
            .address("Springs 3، Emirates Hills (Behind Spinneys/Springs Town Centre), Dubai, Dubai, ae")
            .phoneNumber(new PhoneNumber("+971 (0)4 361 9361"))
            .homepageUrl("http://www.dubaibritishschool.ae/")
            .description("Dubai British School Emirates Hills, a Taaleem School, is a dynamic British International School that aims to be at the leading edge of educational innovation and excellence in British international education globally. We provide an international curriculum firmly rooted in the National Curriculum of England and enriched by specialist provision for the Arabic language and a strong performing and visual arts programme, as well as a multitude of sporting and extra-curricular activities and opportunities.\n\nDubai British School prides itself on its inclusive approach to education. We do not discriminate on the grounds of race, nationality, disability or cultural background. We see the diversity of our student and staff population as one of our greatest assets, and we make every attempt to give each and every student full access to our learning programmes.\n\nTeachers are assisted by specialist staff, such as our Extended Learning Co-ordinator, in the identification of and provision for an individual student’s specific or exceptional learning needs. If a student should need additional learning support beyond that available at the school, then the school reserves the right to pass on to parents part or all of the additional cost incurred. However, we would discourage the use of private tutors by parents because we believe that we should be able to meet every student’s learning needs in full and to ensure that each and every student makes good progress in every respect within our stated curriculum.")
            .build();

    assertEquals(expectedLinkedInSchool, actualLinkedInSchool);
  }
=======
=======
    String jsonFilePath = "linkedIn/linkedin-school.json";
    ClassPathResource resource = new ClassPathResource(jsonFilePath);
    String linkedInSchoolAsJson =
            StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
>>>>>>> f7688e1 (refactor(LinkedIn): copany and school info refactor)

    LinkedInSchool actualLinkedInSchool =
            objectMapper.readValue(linkedInSchoolAsJson, LinkedInSchool.class);

<<<<<<< HEAD
//        String extractedSchoolName = actualLinkedInSchool.getSchoolName();
//
//        assertEquals("Dubai British School", extractedSchoolName);

        LinkedInSchool expectedLinkedInSchool = LinkedInSchool.builder()
                .schoolName("Dubai British School")
                .schoolType("Private")
                .address("Springs 3، Emirates Hills (Behind Spinneys/Springs Town Centre), Dubai, Dubai, ae")
                .phoneNumber( new PhoneNumber("+971 (0)4 361 9361"))
                .homepageUrl("http://www.dubaibritishschool.ae/")
                .description("Dubai British School Emirates Hills, a Taaleem School, is a dynamic British International School that aims to be at the leading edge of educational innovation and excellence in British international education globally. We provide an international curriculum firmly rooted in the National Curriculum of England and enriched by specialist provision for the Arabic language and a strong performing and visual arts programme, as well as a multitude of sporting and extra-curricular activities and opportunities.\n\nDubai British School prides itself on its inclusive approach to education. We do not discriminate on the grounds of race, nationality, disability or cultural background. We see the diversity of our student and staff population as one of our greatest assets, and we make every attempt to give each and every student full access to our learning programmes.\n\nTeachers are assisted by specialist staff, such as our Extended Learning Co-ordinator, in the identification of and provision for an individual student’s specific or exceptional learning needs. If a student should need additional learning support beyond that available at the school, then the school reserves the right to pass on to parents part or all of the additional cost incurred. However, we would discourage the use of private tutors by parents because we believe that we should be able to meet every student’s learning needs in full and to ensure that each and every student makes good progress in every respect within our stated curriculum.")
                .build();

        assertEquals(expectedLinkedInSchool, actualLinkedInSchool);
    }
>>>>>>> e9bc661 (temporary Commit for infoExtractor):src/test/java/io/connectvitae/connectvitaelibrary/linkedIn/models/LinkedInSchoolTest.java
=======

    LinkedInSchool expectedLinkedInSchool = LinkedInSchool.builder()
            .basicSchoolInfo(
                LinkedInSchool.BasicSchoolInfo.builder()
                    .miniSchool(
                        LinkedInSchool.MiniSchool.builder()
                                .active(true)
                                .schoolName("Dubai British School")
                                .build())
                    .build()
                )
            .address("Springs 3، Emirates Hills (Behind Spinneys/Springs Town Centre), Dubai, Dubai, ae")
            .phoneNumber(new PhoneNumber("+971 (0)4 361 9361"))
            .homepageUrl("http://www.dubaibritishschool.ae/")
            .description("Dubai British School Emirates Hills, a Taaleem School, is a dynamic British International School that aims to be at the leading edge of educational innovation and excellence in British international education globally. We provide an international curriculum firmly rooted in the National Curriculum of England and enriched by specialist provision for the Arabic language and a strong performing and visual arts programme, as well as a multitude of sporting and extra-curricular activities and opportunities.\n\nDubai British School prides itself on its inclusive approach to education. We do not discriminate on the grounds of race, nationality, disability or cultural background. We see the diversity of our student and staff population as one of our greatest assets, and we make every attempt to give each and every student full access to our learning programmes.\n\nTeachers are assisted by specialist staff, such as our Extended Learning Co-ordinator, in the identification of and provision for an individual student’s specific or exceptional learning needs. If a student should need additional learning support beyond that available at the school, then the school reserves the right to pass on to parents part or all of the additional cost incurred. However, we would discourage the use of private tutors by parents because we believe that we should be able to meet every student’s learning needs in full and to ensure that each and every student makes good progress in every respect within our stated curriculum.")
            .build();

    assertEquals(expectedLinkedInSchool, actualLinkedInSchool);
  }
>>>>>>> f7688e1 (refactor(LinkedIn): copany and school info refactor)
}
