package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import feign.form.FormProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LinkedInAuthenticationDTO {
  @JsonProperty("session_key")
  @FormProperty("session_key")
  private String sessionKey;
  @JsonProperty("session_password")
  @FormProperty("session_password")
  private String sessionPassword;
}