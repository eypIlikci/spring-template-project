package com.template.model.enums;

import lombok.Getter;

@Getter
public enum GenericConstants {
    //TODO Successful Code Start: 100
    RESET_PASSWORD("101"),
    DELETED("103"),
    UPDATED("104"),
    ADDED("108"),
    CREATE("114"),;
    private final String codes;

    GenericConstants(String codes) {
        this.codes = codes;
    }
}

