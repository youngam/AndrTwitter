package misiulia.alex.dev.andrtwitter.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateFormatter {
    public static final String MONTH_DAY_FORMAT = "MMM d";
                                                        // Thu Oct 26 07:31:08 +0000 2017
    public static final String TWITTER_RESPONSE_FORMAT="EEE MMM dd HH:mm:ss ZZZZZ yyyy";

    public static String format(String dateRaw, String format) {
        SimpleDateFormat utcFormat = new SimpleDateFormat(TWITTER_RESPONSE_FORMAT, Locale.ROOT);
        SimpleDateFormat clientFormat = new SimpleDateFormat(format, Locale.ROOT);
        try {
            Date date = utcFormat.parse(dateRaw);
        return clientFormat.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
