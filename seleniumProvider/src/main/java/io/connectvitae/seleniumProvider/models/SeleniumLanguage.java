package io.connectvitae.seleniumProvider.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SeleniumLanguage {
  private String languageName;
  private String languageLevel;
}
