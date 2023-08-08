package io.connectvitae.connectvitaelibrary.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String adresse; //TODO: change the name
    private String bio;

}
