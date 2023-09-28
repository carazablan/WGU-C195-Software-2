package helper;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/** This is the MyTimeZone.java class. */
public class MyTimeZone {

    /** This method shows local time.
     * @return formattedDate*/
    public static String myLocalTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    /** This method converts time between time zones.
     * @return nyToLocalZDT*/
    public static ZonedDateTime nyTime() {
        LocalDate nyDate = LocalDate.of(2022,07,28);
        LocalTime nyTime = LocalTime.of(8,00);
        ZoneId nyZoneId = ZoneId.of("America/New_York");
        ZonedDateTime nyZDT = ZonedDateTime.of(nyDate, nyTime, nyZoneId);

        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

        ZonedDateTime nyToLocalZDT = nyZDT.withZoneSameInstant(localZoneId);

        return nyToLocalZDT;
    }
}
