package io.connectvitae.connectvitaelibrary.linkedIn.models.views;

import io.connectvitae.connectvitaelibrary.linkedIn.models.LinkedInSkill;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillView {
    public List<LinkedInSkill> elements;
}
