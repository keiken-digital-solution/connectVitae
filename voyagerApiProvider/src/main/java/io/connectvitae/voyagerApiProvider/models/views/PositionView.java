package io.connectvitae.voyagerApiProvider.models.views;

import io.connectvitae.voyagerApiProvider.models.VoyagerApiPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionView {
  private List<VoyagerApiPosition> elements;
}
