package com.kotbegemot.testtask1.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Service exception thrown when the news page index in the request is incorrect
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "There is no page with such number")
public class IllegalPageNumberException extends RuntimeException {
}
