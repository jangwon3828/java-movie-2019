package utils;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeUtilsTest {
    @Test
    public void createDateTime() {
        LocalDateTime dateTime = DateTimeUtils.createDateTime("2022-12-10 11:23");
        assertThat(dateTime.getYear()).isEqualTo(2022);
        assertThat(dateTime.getMonthValue()).isEqualTo(4);
        assertThat(dateTime.getDayOfMonth()).isEqualTo(10);
        assertThat(dateTime.getHour()).isEqualTo(11);
        assertThat(dateTime.getMinute()).isEqualTo(23);
    }

    @Test
    public void isOneHourWithinRange() {
        LocalDateTime dateTime = DateTimeUtils.createDateTime("2022-12-10 11:23");
        LocalDateTime afterDateTime = DateTimeUtils.createDateTime("2022-12-10 12:22");
        assertThat(DateTimeUtils.isOneHourWithinRange(dateTime, afterDateTime)).isTrue();

        LocalDateTime beforeDateTime = DateTimeUtils.createDateTime("2022-12-10 10:24");
        assertThat(DateTimeUtils.isOneHourWithinRange(dateTime, beforeDateTime)).isTrue();
    }

    @Test
    public void isOneHourWithoutRange() {
        LocalDateTime dateTime = DateTimeUtils.createDateTime("2022-12-10 11:23");
        LocalDateTime afterDateTime = DateTimeUtils.createDateTime("2022-12-10 12:24");
        assertThat(DateTimeUtils.isOneHourWithinRange(dateTime, afterDateTime)).isFalse();

        LocalDateTime beforeDateTime = DateTimeUtils.createDateTime("2022-12-10 10:22");
        assertThat(DateTimeUtils.isOneHourWithinRange(dateTime, beforeDateTime)).isFalse();
    }
}