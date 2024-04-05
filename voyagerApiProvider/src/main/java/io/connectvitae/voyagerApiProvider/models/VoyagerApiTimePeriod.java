package io.connectvitae.voyagerApiProvider.models;

import io.connectvitae.voyagerApiProvider.utils.VoyagerDateParser;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoyagerApiTimePeriod {
  @JsonDeserialize(using = VoyagerDateParser.class)
  private Date startDate;
  @JsonDeserialize(using = VoyagerDateParser.class)
  private Date endDate;
}
