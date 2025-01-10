package com.openclassrooms.chatop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Something is wrong with your your request")
public class BadRequestException  extends RuntimeException {

}
