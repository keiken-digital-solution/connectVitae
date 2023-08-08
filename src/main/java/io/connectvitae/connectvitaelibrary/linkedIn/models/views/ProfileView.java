package io.connectvitae.connectvitaelibrary.linkedIn.models.views;

import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileView {
    public LinkedInProfile profile;
    public CertificationView certificationView;
    public EducationView educationView;
    public PositionView positionView;
    public SkillView skillView;
}
