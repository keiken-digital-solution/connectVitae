package io.connectvitae.connectvitaelibrary.linkedIn.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkedInProfile {
  private String firstName;
  private String lastName;
  private String summary;
  private String headline;
  private String industryName;
  private String geoLocationName;
}
