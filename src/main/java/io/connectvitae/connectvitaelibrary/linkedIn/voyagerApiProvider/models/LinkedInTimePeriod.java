package io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.models;

import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.utils.LinkedInDateDeserializer;

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
public class LinkedInTimePeriod {
  @JsonDeserialize(using = LinkedInDateDeserializer.class)
  private Date startDate;
  @JsonDeserialize(using = LinkedInDateDeserializer.class)
  private Date endDate;
}
