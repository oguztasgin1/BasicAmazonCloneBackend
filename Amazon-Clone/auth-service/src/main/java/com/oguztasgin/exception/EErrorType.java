package com.oguztasgin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@AllArgsConstructor
public enum EErrorType {
    BAD_REQUEST_ERROR(1201,"You Have Entered Invalid Parameters.",BAD_REQUEST),
    AUTH_PASSWORD_ERROR(1301,"Passwords do not match.",BAD_REQUEST),
    AUTH_USERNAME_ERROR(1302,"Username is already registered.",BAD_REQUEST),
    AUTH_LOGIN_ERROR(1303,"Username or password is incorrect.",BAD_REQUEST),
    INTERNAL_ERROR(3000,"Unexpected error on server.",INTERNAL_SERVER_ERROR),
    TOKEN_ERROR(3001,"Token creation error.",INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(2301,"No user found for the id you are looking for.",INTERNAL_SERVER_ERROR),
    ROLE_NOT_FOUND(4117,"This user role has not found",HttpStatus.BAD_REQUEST);
    private int code;
    private String message;
    private HttpStatus httpStatus;


}
