package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInEducation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationView {
  private List<LinkedInEducation> elements;
}
