package com.kotbegemot.testtask1.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Service exception thrown when the image index in the request is incorrect
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "there is no image with such id")
public class ImageNotFoundException extends RuntimeException {
}
