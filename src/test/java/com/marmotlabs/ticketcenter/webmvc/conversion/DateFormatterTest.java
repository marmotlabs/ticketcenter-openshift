package com.marmotlabs.ticketcenter.webmvc.conversion;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Zuui
 */
public class DateFormatterTest {

    @Test
    public void testParse() throws ParseException {
        DateFormatter df = new DateFormatter();
        Date date = df.parse("08-06-1985", null);

        Assert.assertNotNull(date);

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);

        Assert.assertEquals(8, cal.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(5, cal.get(Calendar.MONTH));
        Assert.assertEquals(1985, cal.get(Calendar.YEAR));
    }

    @Test
    public void testPrint() {
        DateFormatter df = new DateFormatter();

        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 8);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 1985);

        Date date = cal.getTime();

        String stringDate = df.print(date, null);

        Assert.assertNotNull(stringDate);
        Assert.assertEquals("08-06-1985", stringDate);
    }

}
