package com.kotbegemot.testtask1.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Service exception thrown when the news index in the request is incorrect
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "There is no news with such id")
public class NewsNotFoundException extends RuntimeException {
}