package io.connectvitae.contracts.models;

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
  private String companyName;
  private String companyEmployeeCountRange;
  private String companyWebSiteUrl;
  private String companyPhoneNumber;
  private String companyType;
  private List<String> companySpecialities;
  private String companyDescription;
  private List<Location> companyLocations;

}
