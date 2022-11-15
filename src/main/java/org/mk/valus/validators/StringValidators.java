package org.mk.valus.validators;

import org.mk.valus.Validator;
import org.mk.valus.predicators.StringPredicators;

public class StringValidators {

    public static Validator<String> isNull() {
        return Validator.from(StringPredicators.isNull(), str -> String.format("String value is '%s', but null value was expected", str));
    }
}
