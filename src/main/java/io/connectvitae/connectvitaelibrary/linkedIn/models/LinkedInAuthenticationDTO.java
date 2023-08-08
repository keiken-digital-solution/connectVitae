package io.connectvitae.connectvitaelibrary.linkedIn.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LinkedInAuthenticationDTO {
    // TODO: rename this to camel case and generate the body with snake case
    private String session_key;
    private String session_password;
}
