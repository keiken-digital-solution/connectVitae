package io.connectvitae.voyagerApiProvider.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoyagerApiSchool {
  private String address;
  @JsonProperty("phoneNumber")
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


