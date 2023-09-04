package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkedInCompany {
  private String name;
  private String universalName;
  private String staffCount;
  private String websiteUrl;
  private CompanyType companyType;
  private List<String> industries;
  private String description;
  private String companyPageUrl;
  private List<String> specialities;
  private PhoneNumber phone;
  private List<LinkedInCompanyLocation> confirmedLocations;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class CompanyType {
    private String localizedName;
  }

}


