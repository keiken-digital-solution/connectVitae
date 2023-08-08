package io.connectvitae.connectvitaelibrary.linkedIn.models.views;

import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInPosition;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionView {
    public List<LinkedInPosition> elements;
}
