package com.openclassrooms.chatop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Something wrong in the request")
public class BadRequestException  extends RuntimeException {
}
