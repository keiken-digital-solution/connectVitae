package io.connectvitae.connectvitaelibrary.linkedIn.models;

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
  private String employeeCountRange;
  private String websiteUrl;
  private String companyType;
  private List<String> industries;
  private String description;
}
