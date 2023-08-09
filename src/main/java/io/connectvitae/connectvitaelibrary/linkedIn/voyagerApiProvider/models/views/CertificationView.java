package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.views;

import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.LinkedInCertification;
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
