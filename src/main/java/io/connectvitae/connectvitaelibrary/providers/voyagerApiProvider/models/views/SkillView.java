package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.views;

import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models.LinkedInSkill;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillView {
  private List<LinkedInSkill> elements;
}
