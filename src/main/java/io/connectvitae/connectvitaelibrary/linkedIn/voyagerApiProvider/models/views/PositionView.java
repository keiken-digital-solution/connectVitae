package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.views;

import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models.LinkedInPosition;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionView {
  private List<LinkedInPosition> elements;
}
