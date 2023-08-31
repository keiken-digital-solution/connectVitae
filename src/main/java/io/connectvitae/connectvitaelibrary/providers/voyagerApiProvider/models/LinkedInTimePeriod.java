package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.utils.VoyagerDateParser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkedInTimePeriod {
  @JsonDeserialize(using = VoyagerDateParser.class)
  private Date startDate;
  @JsonDeserialize(using = VoyagerDateParser.class)
  private Date endDate;
}
