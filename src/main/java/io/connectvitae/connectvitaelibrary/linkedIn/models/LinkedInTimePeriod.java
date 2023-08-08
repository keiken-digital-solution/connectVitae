package io.connectvitae.connectvitaelibrary.linkedIn.models;

import io.connectvitae.connectvitaelibrary.linkedIn.utils.LinkedInDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkedInTimePeriod {
    @JsonDeserialize(using = LinkedInDateDeserializer.class)
    private Date startDate;
    @JsonDeserialize(using = LinkedInDateDeserializer.class)
    private Date endDate;
}
