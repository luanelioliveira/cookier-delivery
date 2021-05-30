package com.cookierdelivery.msproducts.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

  public final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
  public final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

  private static final String DD_MM_YYYY = "dd/MM/yyyy";

  /** Method responsible to convert localDate to brazilian format {@link DateUtils#DD_MM_YYYY} */
  public String asString(LocalDate date) {
    return asString(date, DD_MM_YYYY);
  }

  /** Method responsible to convert localDate to brazilian format {@link DateUtils#DD_MM_YYYY} */
  public String asString(LocalDateTime dateTime) {
    return asString(dateTime, DD_MM_YYYY);
  }

  /** Method responsible to return hour, minute and second from LocalDateTime Object as String */
  public String asTime(LocalDateTime dateTime) {
    return dateTime.format(TIME_FORMATTER);
  }

  /** Method responsible to convert localDateTime to string format */
  public String asString(LocalDateTime dateTime, String pattern) {
    return dateTime.format(DateTimeFormatter.ofPattern(pattern));
  }

  /** Method responsible to convert localDateTime to string format */
  public String asString(LocalDate date, String pattern) {
    return date.format(DateTimeFormatter.ofPattern(pattern));
  }

  /** Convert string to LocalDate. String format expected 2021-03-31 */
  public LocalDate asLocalDate(String date) {
    return LocalDate.parse(date);
  }

  /** Convert string to LocalDateTime. String format expected 2021-03-08T12:13:14 */
  public LocalDateTime asLocalDateTime(String localDateTime) {
    return LocalDateTime.parse(localDateTime);
  }
}
