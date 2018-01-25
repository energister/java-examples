package time;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ZonedDateTimeTest {
    @Test
    public void toStringConversions() {
        ZonedDateTime dt = ZonedDateTime.of(2018, 01, 25, 18, 20, 05, 00, ZoneId.of("Europe/Moscow"));

        assertThat(dt.toString(), equalTo("2018-01-25T18:20:05+03:00[Europe/Moscow]"));
        assertThat(dt.withFixedOffsetZone().toString(), equalTo("2018-01-25T18:20:05+03:00"));
        assertThat(dt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), equalTo("2018-01-25T18:20:05+03:00"));
    }
}
