package com.example.haibazoshop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    CATEGORY_NOT_FOUND(404, "CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND),
    COLOR_NOT_FOUND(404, "COLOR_NOT_FOUND", HttpStatus.NOT_FOUND),
    SIZE_NOT_FOUND(404, "SIZE_NOT_FOUND", HttpStatus.NOT_FOUND);
    ;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
