package com.marmotlabs.ticketcenter.webmvc.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Zuui
 */
public class DateFormatter implements Formatter<Date> {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public DateFormatter() {
        super();
    }

    @Override
    public Date parse(final String text, final Locale locale) throws ParseException {
        final SimpleDateFormat dateFormat = createDateFormat(locale);
        return dateFormat.parse(text);
    }

    @Override
    public String print(final Date object, final Locale locale) {
        final SimpleDateFormat dateFormat = createDateFormat(locale);
        return dateFormat.format(object);
    }

    private SimpleDateFormat createDateFormat(final Locale locale) {
        //TODO - normally the date format should be obtained from a resource bundle, based on the locale
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);
        return dateFormat;
    }

}
