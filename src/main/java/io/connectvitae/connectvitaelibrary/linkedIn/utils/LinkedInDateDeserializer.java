package io.connectvitae.connectvitaelibrary.linkedIn.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

public class LinkedInDateDeserializer extends StdDeserializer<Date> {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-yyyy"); // Adjust the format accordingly

    protected LinkedInDateDeserializer() {
        this(null);
    }

    protected LinkedInDateDeserializer(Class<?> vc) {
        super(vc);
    }


    @Data
    private static class DateInfo {
        public String year;
        public String month;
    }
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        DateInfo dateInfo = jsonParser.readValueAs(DateInfo.class);

        String year = dateInfo.getYear();
        String month = dateInfo.getMonth();
        try {
            String dateString = month != null ?
                    month + "-" + year
                    : "01-" + year;
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            throw new IOException("Failed to parse date", e);
        }
    }
}
