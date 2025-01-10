package com.openclassrooms.chatop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No item was found")
public class NotFoundException extends RuntimeException {
}
