package io.connectvitae.voyagerApiProvider.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.Data;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VoyagerDateParser extends StdDeserializer<Date> {
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-yyyy"); // Adjust the format accordingly

  protected VoyagerDateParser() {
    this(null);
  }

  protected VoyagerDateParser(Class<?> vc) {
    super(vc);
  }

  @Override
  public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    DateInfo dateInfo = jsonParser.readValueAs(DateInfo.class);

    String year = dateInfo.getYear();
    String month = dateInfo.getMonth();
    try {
      String dateString = month != null
          ? month + "-" + year
          : "01-" + year;
      return DATE_FORMAT.parse(dateString);
    } catch (ParseException e) {
      throw new IOException("Failed to parse date", e);
    }
  }

  @Data
  private static class DateInfo {
    private String year;
    private String month;
  }
}
