package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
@Component
public class DateParser {
    static final String[] patterns = {
            "MMM yyyy",
            "MMM. yyyy",
            "MMMM yyyy",
            "MMMM. yyyy",
            "yyyy"
    };
    static final Locale[] supportedLanguages = new Locale[]{
            Locale.FRENCH,
            Locale.ENGLISH
    };

    public static Date[] extractDates(String dateText) {
        String[] parts = dateText.split(" - ");
        return
                new Date[]{
                parseDate(reformDate(parts[0])),
                parseDate(reformDate(parts[1]))
        };
    }

    public static String reformDate(String textPart){
        return  textPart.split(" ").length > 1 ?
                textPart.split(" ")[0] + " " + textPart.split(" ")[1]
                : textPart.split(" ")[0];
    }
    public static Date parseDate(String dateString) {
        for (String pattern : patterns) {
            for (Locale locale : supportedLanguages) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
                try {
                    return sdf.parse(dateString);
                } catch (ParseException ignored) {
                    // Ignore ParseException and try the next locale
                }
            }
        }
        return null;
    }
}
