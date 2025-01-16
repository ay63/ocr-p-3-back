package com.openclassrooms.chatop.services;

import java.time.Instant;

public interface DateService {

     String instantObjectToDateFormatString(Instant date);
     Instant DateFormatStringToInstant(String date);
}
