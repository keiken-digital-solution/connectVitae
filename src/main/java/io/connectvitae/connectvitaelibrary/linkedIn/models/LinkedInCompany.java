package io.connectvitae.connectvitaelibrary.linkedIn.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkedInCompany {
    public String name;
    public String universalName;
    public String employeeCountRange;
    public String websiteUrl;
    public String companyType;
    public List<String> industries;
    public String description;
}
