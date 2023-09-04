package io.connectvitae.connectvitaelibrary.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {
    // TODO: exemple de data Model d'un client
    private String schoolName;
    private String schoolType;
    private String schoolAddress;
    private String schoolNumber;
    private String schoolUrl;
    private String schoolDescription;


}
