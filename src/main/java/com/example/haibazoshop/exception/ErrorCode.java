package com.example.haibazoshop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_UPLOAD_ERROR(400, "IMAGE_UPLOAD_ERROR", HttpStatus.BAD_REQUEST),
    BAD_REQUEST(400, "BAD_REQUEST", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_ALREADY_EXISTS(400, "PRODUCT_NAME_ALREADY_EXISTS", HttpStatus.BAD_REQUEST),
    IMAGE_DELETE_ERROR(400, "IMAGE_DELETE_ERROR", HttpStatus.BAD_REQUEST),

    CATEGORY_NOT_FOUND(404, "CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(404, "PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND),
    COLOR_NOT_FOUND(404, "COLOR_NOT_FOUND", HttpStatus.NOT_FOUND),
    REVIEW_NOT_FOUND(404, "REVIEW_NOT_FOUND", HttpStatus.NOT_FOUND),
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
