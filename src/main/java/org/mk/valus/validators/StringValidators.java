package org.mk.valus.validators;

import org.mk.valus.Validator;
import org.mk.valus.predicators.StringPredicators;

public class StringValidators {

    public static Validator<String> isNull() {
        return Validator.from(
                StringPredicators.isNull(),
                str -> String.format("String value is '%s', but null value was expected", str),
                ErrorCodes.STRING_NOT_NULL);
    }

    public static Validator<String> isPostcode() {
        String postcodeRegex = "\\d{2}-\\d{3}";
        return Validator.from(
                StringPredicators.match(postcodeRegex),
                str -> String.format("String value is '%s', but postcode expected format is: %s", str, postcodeRegex),
                ErrorCodes.WRONG_POSTCODE);
    }
}
