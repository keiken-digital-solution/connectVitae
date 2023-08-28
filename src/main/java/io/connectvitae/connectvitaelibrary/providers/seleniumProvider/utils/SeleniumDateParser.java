package io.connectvitae.connectvitaelibrary.providers.seleniumProvider.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
@Component
public class SeleniumDateParser {
    static final String[] patterns = {
            "MMM yyyy",
            "MMM. yyyy",
            "MMMM yyyy",
            "MMMM. yyyy",
            "yyyy",
            "DDDD"
    };
    static final Locale[] supportedLanguages = new Locale[]{
            Locale.FRENCH,
            Locale.ENGLISH
    };



    public static Date[] extractDates(String dateText) {

        return  dateText != "" ?
                new Date[]{
                        parseDate(reformDate(dateText.split("-")[0]).trim()),
                        parseDate(reformDate(dateText.split("-")[1].trim()))
                }
                : new Date[] {null, null};
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
