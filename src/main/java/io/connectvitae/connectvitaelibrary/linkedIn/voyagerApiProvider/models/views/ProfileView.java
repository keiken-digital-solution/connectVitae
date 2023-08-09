package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.views;

import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.LinkedInProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileView {
  private LinkedInProfile profile;
  private CertificationView certificationView;
  private EducationView educationView;
  private PositionView positionView;
  private SkillView skillView;
}
