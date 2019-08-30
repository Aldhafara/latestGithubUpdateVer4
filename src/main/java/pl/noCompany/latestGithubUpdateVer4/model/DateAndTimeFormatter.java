package pl.noCompany.latestGithubUpdateVer4.model;

import java.time.format.DateTimeFormatter;

public class DateAndTimeFormatter {

    private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
    }
}
