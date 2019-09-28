package com.guanocoder.javafxapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate localDateFromString(String dateString) {
        return LocalDate.parse(dateString, formatter);
    }

    public static String dateStringFromLocalDate(LocalDate date) {
        return date.format(formatter);
    }
}
