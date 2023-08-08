package io.connectvitae.connectvitaelibrary.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private List<Experience> experiences;
    private List<Skill> skills;
    private List <Certification> certifications;
    private List<Training> trainings;
    private User user;
}
