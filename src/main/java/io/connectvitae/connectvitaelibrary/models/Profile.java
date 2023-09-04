package io.connectvitae.connectvitaelibrary.models;

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
public class Profile {
<<<<<<< HEAD

  private List<Experience> experiences;
  private List<Skill> skills;
  private List<Certification> certifications;
  private List<Education> educations;
  private User user;
  private List<Company> companies;
=======
    private List<Experience> experiences;
    private List<Skill> skills;
    private List <Certification> certifications;
    private List<Training> trainings;
    private List<Company> companies;
    private User user;
>>>>>>> e112ba9 (feat(LinkedIn): company & school extraction)
}
