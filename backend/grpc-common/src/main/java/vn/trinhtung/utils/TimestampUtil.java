package vn.trinhtung.utils;

import com.google.protobuf.Timestamp;

import java.time.*;

public class TimestampUtil {
    public static Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

    public static Timestamp localDateToTimestamp(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

    public static LocalDate timestampToLocalDate(Timestamp timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
        ZoneId z = ZoneId.systemDefault();
        return instant.atZone(z).toLocalDate();
    }

    public static LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
        ZoneId z = ZoneId.systemDefault();
        return instant.atZone(z).toLocalDateTime();
    }
}
