package io.connectvitae.connectvitaelibrary.models;

<<<<<<< HEAD
<<<<<<< HEAD
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInCompanyLocation;
=======
import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInCompanyLocation;
>>>>>>> e9bc661 (temporary Commit for infoExtractor)
=======
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInCompanyLocation;
>>>>>>> f7688e1 (refactor(LinkedIn): copany and school info refactor)
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> f7688e1 (refactor(LinkedIn): copany and school info refactor)
  private String companyName;
  private String companyEmployeeCountRange;
  private String companyWebSiteUrl;
  private String companyPhoneNumber;
  private String companyType;
  private List<String> companySpecialities;
  private String companyDescription;
  private List<LinkedInCompanyLocation> companyLocations;
<<<<<<< HEAD
=======

    private String companyName;
    private String companyUrl;
    private String companyUniversalName;
    private String companyPhoneNumber;
    private List<String> companySpecialities;
<<<<<<< HEAD
    private String CompanyDescription;
    private List<LinkedInCompanyLocation> CompanyConfirmedLocations;
    // TODO: exemple de data Model d'un client
>>>>>>> e9bc661 (temporary Commit for infoExtractor)
=======
    private String companyDescription;
    private List<LinkedInCompanyLocation> companyConfirmedLocations;
>>>>>>> e112ba9 (feat(LinkedIn): company & school extraction)
=======
>>>>>>> f7688e1 (refactor(LinkedIn): copany and school info refactor)
}
