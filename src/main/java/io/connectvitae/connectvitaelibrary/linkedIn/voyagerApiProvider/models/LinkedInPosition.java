package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkedInPosition {
  private String locationName;
  private String companyName;
  private LinkedInTimePeriod timePeriod;
  private String description;
  private String title;
}
