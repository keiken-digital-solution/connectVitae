package io.connectvitae.connectvitaelibrary.providers.seleniumProvider.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeleniumExperience {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date endDate;
  private String company;
  private String roleName;
  private String mission;
  private String projectType;
  private String domain;
  private String location;
}
