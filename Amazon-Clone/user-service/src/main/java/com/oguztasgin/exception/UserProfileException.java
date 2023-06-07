package com.oguztasgin.exception;

import lombok.Getter;

@Getter
public class UserProfileException extends RuntimeException{
    private final EErrorType EErrorType;

    /**
     * Since we inherit it from the runtime, the error message must be passed to it.
     * @param EErrorType
     */
    public UserProfileException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }

    public UserProfileException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }
}
