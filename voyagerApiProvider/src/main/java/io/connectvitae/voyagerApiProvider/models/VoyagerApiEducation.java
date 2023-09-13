package io.connectvitae.voyagerApiProvider.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoyagerApiEducation {
  private VoyagerApiTimePeriod timePeriod;
  private String fieldOfStudy;
  private String schoolName;
  private String degreeName;
  private String grade;
}
