package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views;

import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.VoyagerApiProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileView {
  private VoyagerApiProfile profile;
  private CertificationView certificationView;
  private EducationView educationView;
  private PositionView positionView;
  private SkillView skillView;
}
