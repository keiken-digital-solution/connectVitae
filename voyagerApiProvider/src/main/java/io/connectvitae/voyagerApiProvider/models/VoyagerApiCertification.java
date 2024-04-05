package io.connectvitae.voyagerApiProvider.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoyagerApiCertification {
  private String name;
  private String authority;
  private VoyagerApiTimePeriod timePeriod;
}
