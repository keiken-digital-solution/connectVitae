package io.connectvitae.contracts.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
  private String country;
  private String geographicArea;
  private String city;
  private String postalCode;
  private String description;
  private String line1;
  private String line2;
}

