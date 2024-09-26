package com.example.haibazoshop.exception;

public class AppException extends RuntimeException{
    private ErrorCode errorCode;
    private String customMessage;
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.customMessage = errorCode.getMessage();
    }
    public AppException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }
    public AppException(ErrorCode errorCode, String customMessage, Throwable cause) {
        super(customMessage, cause);
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public String getCustomMessage() {
        return customMessage;
    }
    @Override
    public String getMessage(){
        return String.format("Error Code: %s, Message: %s", errorCode.getCode(), customMessage);
    }
}
