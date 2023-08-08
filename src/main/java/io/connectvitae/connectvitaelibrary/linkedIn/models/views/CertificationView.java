package io.connectvitae.connectvitaelibrary.linkedIn.models.views;

import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInCertification;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationView {
    private List<LinkedInCertification> elements;
}
