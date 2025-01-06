package com.openclassrooms.chatop.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "your are not allow to access this resource")
public class UnauthorizedException extends RuntimeException {
}
