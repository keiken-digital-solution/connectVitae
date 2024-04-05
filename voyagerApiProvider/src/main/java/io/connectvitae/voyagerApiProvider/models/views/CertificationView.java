package io.connectvitae.voyagerApiProvider.models.views;


import io.connectvitae.voyagerApiProvider.models.VoyagerApiCertification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationView {
  private List<VoyagerApiCertification> elements;
}
