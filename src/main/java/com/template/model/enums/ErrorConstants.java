package com.template.model.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public enum ErrorConstants {

    VALIDATION(BAD_REQUEST,"000"),
    EX_CONFIRM_TOKEN(BAD_REQUEST,"001"),
    WRONG_THIS_ID(BAD_REQUEST,"003"),
    EMPTY_VALUE(BAD_REQUEST,"004"),
    LIMITED_ACTION(BAD_REQUEST,"005"),
    REPETITIVE_ACTION(BAD_REQUEST,"006"),
    FILE_NAME(BAD_REQUEST,"007"),
    LOGIN(BAD_REQUEST,"008"),
    JWT(BAD_REQUEST,"009"),
    UNAUTHORIZD(UNAUTHORIZED,"010"),
    FORBIDDN(FORBIDDEN,"011"),
    SERVER(BAD_REQUEST,"012"),
    BAD_RQUEST(BAD_REQUEST,"013"),
    UPDATE_ERROR(BAD_REQUEST,"014"),
    FILE_EXCP(BAD_REQUEST,"015"),
    ADDED(BAD_REQUEST,"016"),
    USED_EMAIL(BAD_REQUEST,"017"),
    VERSION(BAD_REQUEST,"018"),
    ;
    private final HttpStatus httpStatus;
    private final String codes;
    ErrorConstants(HttpStatus status, String codes) {
        this.httpStatus = status;
        this.codes = codes;
    }
}
