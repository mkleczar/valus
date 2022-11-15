package org.mk.valus.validators;

import org.mk.valus.Validator;

import java.util.Objects;
import java.util.regex.Pattern;

public class StringValidators {

    public static Validator<String> isNull() {
        return Objects::isNull;
    }

    public static Validator<String> isNotNull() {
        return Validator.not(isNull());
    }

    public static Validator<String> isEmpty() {
        return Validator.and(isNotNull(), String::isEmpty);
    }

    public static Validator<String> isNotEmpty() {
        return Validator.and(isNotNull(), Validator.not(isEmpty()));
    }

    public static Validator<String> match(String regex) {
        return t -> Pattern.matches(regex, t);
    }

    public static Validator<String> notMatch(String regex) {
        return Validator.not(match(regex));
    }
}
