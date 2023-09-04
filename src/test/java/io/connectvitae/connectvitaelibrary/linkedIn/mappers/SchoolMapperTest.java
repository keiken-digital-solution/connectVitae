//package io.connectvitae.connectvitaelibrary.linkedIn.mappers;
//
//import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInSchool;
//import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.PhoneNumber;
//import io.connectvitae.connectvitaelibrary.models.School;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class SchoolMapperTest {
//  @Autowired
//  private SchoolMapper schoolMapper;
//
//  @Test
//  void testConvertSchool() {
//    LinkedInSchool linkedInSchool =
//            LinkedInSchool.builder()
//                    .basicSchoolInfo(
//                            LinkedInSchool.BasicSchoolInfo.builder()
//                                    .miniSchool(
//                                            LinkedInSchool.MiniSchool.builder()
//                                            .schoolName("Dubai British School")
//                                            .build())
//                                    .build()
//                    )
//                    .schoolType("Private")
//                    .phoneNumber(new PhoneNumber("+971 (0)4 361 9361)"))
//                    .address("Springs 3، Emirates Hills (Behind Spinneys/Springs Town Centre), Dubai, Dubai, ae").homepageUrl("http://www.dubaibritishschool.ae/")
//                    .description("Dubai British School Emirates Hills, a Taaleem School, is a dynamic British International School that aims to be at the leading edge of educational innovation and excellence in British international education globally. We provide an international curriculum firmly rooted in the National Curriculum of England and enriched by specialist provision for the Arabic language and a strong performing and visual arts programme, as well as a multitude of sporting and extra-curricular activities and opportunities.\n\nDubai British School prides itself on its inclusive approach to education. We do not discriminate on the grounds of race, nationality, disability or cultural background. We see the diversity of our student and staff population as one of our greatest assets, and we make every attempt to give each and every student full access to our learning programmes.\n\nTeachers are assisted by specialist staff, such as our Extended Learning Co-ordinator, in the identification of and provision for an individual student’s specific or exceptional learning needs. If a student should need additional learning support beyond that available at the school, then the school reserves the right to pass on to parents part or all of the additional cost incurred. However, we would discourage the use of private tutors by parents because we believe that we should be able to meet every student’s learning needs in full and to ensure that each and every student makes good progress in every respect within our stated curriculum.")
//                    .build();
//
//    School expectedSchool =
//            School.builder()
//                    .schoolName("Dubai British School")
//                    .schoolType("Private")
//                    .schoolNumber(("+971 (0)4 361 9361)"))
//                    .schoolAddress("Springs 3، Emirates Hills (Behind Spinneys/Springs Town Centre), Dubai, Dubai, ae")
//                    .schoolUrl("http://www.dubaibritishschool.ae/")
//                    .schoolDescription("Dubai British School Emirates Hills, a Taaleem School, is a dynamic British International School that aims to be at the leading edge of educational innovation and excellence in British international education globally. We provide an international curriculum firmly rooted in the National Curriculum of England and enriched by specialist provision for the Arabic language and a strong performing and visual arts programme, as well as a multitude of sporting and extra-curricular activities and opportunities.\n\nDubai British School prides itself on its inclusive approach to education. We do not discriminate on the grounds of race, nationality, disability or cultural background. We see the diversity of our student and staff population as one of our greatest assets, and we make every attempt to give each and every student full access to our learning programmes.\n\nTeachers are assisted by specialist staff, such as our Extended Learning Co-ordinator, in the identification of and provision for an individual student’s specific or exceptional learning needs. If a student should need additional learning support beyond that available at the school, then the school reserves the right to pass on to parents part or all of the additional cost incurred. However, we would discourage the use of private tutors by parents because we believe that we should be able to meet every student’s learning needs in full and to ensure that each and every student makes good progress in every respect within our stated curriculum.")
//                    .build();
//
//    School actualSchool = schoolMapper.convertToSchool(linkedInSchool);
//    assertEquals(expectedSchool, actualSchool);
//  }
//}
