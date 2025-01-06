package com.openclassrooms.chatop.services;


import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class DateService {

    private static final String PATTERN_FORMAT = "YYYY/MM/DD";


    public String getFormattedDateToString(Instant date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());

        return formatter.format(date);
    }

    public Instant getFormattedDateToInstant(String date) {
        return  Instant.parse(date);
    }
}
