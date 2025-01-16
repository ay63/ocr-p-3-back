package com.openclassrooms.chatop.services.implementations;

import com.openclassrooms.chatop.services.DateService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class DateServiceImpl implements DateService {

    private static final String PATTERN_FORMAT = "YYYY/MM/DD";

    /**
     * Format Instant into string date format
     *
     * @param date Instant
     * @return String ex: 2025/01/31
     */
    @Override
    public String instantObjectToDateFormatString(Instant date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());

        return formatter.format(date);
    }

    /**
     * Get Instant object from string date (ex: 2025/01/31)
     *
     * @param date Instant
     * @return String ex: 2025/01/31
     */
    @Override
    public Instant DateFormatStringToInstant(String date) {
        return Instant.parse(date);
    }
}
