package org.mk.valus.validators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    // for String
    STRING_NOT_NULL(100),
    STRING_IS_NULL(101),
    WRONG_POSTCODE(190),

    // for Integer
    IS_LESS_OR_EQUAL(500),
    IS_GREATER_OR_EQUAL(501)
    ;

    private int code;
}
