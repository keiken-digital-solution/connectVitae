package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeleniumSkill {
    private String skillName;
    private String skillDescription;
    private List<String> skillEndorsements;
}
