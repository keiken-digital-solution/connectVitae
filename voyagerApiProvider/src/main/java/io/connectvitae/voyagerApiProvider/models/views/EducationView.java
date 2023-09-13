package io.connectvitae.voyagerApiProvider.models.views;

import io.connectvitae.voyagerApiProvider.models.VoyagerApiEducation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationView {
  private List<VoyagerApiEducation> elements;
}
