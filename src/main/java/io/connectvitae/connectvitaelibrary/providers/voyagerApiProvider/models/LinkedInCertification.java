package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkedInCertification {
  private String name;
  private String authority;
  private LinkedInTimePeriod timePeriod;
  private LinkedInCompany company;
}
