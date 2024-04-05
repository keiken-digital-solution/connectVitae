package io.connectvitae.voyagerApiProvider.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoyagerApiProfile {
  private String firstName;
  private String lastName;
  private String summary;
  private String headline;
  private String industryName;
  private String geoLocationName;
}
