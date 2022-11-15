package org.mk.valus.validators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    // for String
    STRING_NOT_NULL(100),
    WRONG_POSTCODE(190),

    // for Integer
    IS_LESS_OR_EQUAL(500)
    ;

    private int code;
}
