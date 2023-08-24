package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeleniumProfile {
    private List<SeleniumExperience> seleniumExperiences;
    private List<SeleniumSkill> seleniumSkills;
    private List <SeleniumCertification> seleniumCertifications;
    private List<SeleniumEducation> seleniumEducations;
    private SeleniumUser seleniumUser;
}
