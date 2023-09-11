package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoyagerApiPosition {
  private String locationName;
  private String companyName;
  private VoyagerApiTimePeriod timePeriod;
  private String description;
  private String title;
}
