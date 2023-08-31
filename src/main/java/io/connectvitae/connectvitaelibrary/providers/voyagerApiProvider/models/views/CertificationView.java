package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views;

import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInCertification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationView {
  private List<LinkedInCertification> elements;
}
