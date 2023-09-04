package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkedInSchool {
  private String address;
  private PhoneNumber phoneNumber;
  private String homepageUrl;
  private BasicSchoolInfo basicSchoolInfo;
  private String description;
  private String schoolType;

  public String getSchoolName() {
    if (basicSchoolInfo != null && basicSchoolInfo.getMiniSchool() != null) {
      return basicSchoolInfo.getMiniSchool().getSchoolName();
    }
    return null;
  }

  @AllArgsConstructor
  @Builder
  @Data
  @NoArgsConstructor
  public static class MiniSchool {
    private boolean active;
    private String schoolName;
  }

  @AllArgsConstructor
  @Builder
  @Data
  @NoArgsConstructor
  public static class BasicSchoolInfo {
    private MiniSchool miniSchool;
  }
}

