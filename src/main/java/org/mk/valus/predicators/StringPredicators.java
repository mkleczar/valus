package org.mk.valus.predicators;

import org.mk.valus.Predicator;

import java.util.Objects;
import java.util.regex.Pattern;

public class StringPredicators {

    public static Predicator<String> isNull() {
        return Objects::isNull;
    }

    public static Predicator<String> isNotNull() {
        return Predicator.not(isNull());
    }

    public static Predicator<String> isEmpty() {
        return Predicator.and(isNotNull(), String::isEmpty);
    }

    public static Predicator<String> isNotEmpty() {
        return Predicator.and(isNotNull(), Predicator.not(isEmpty()));
    }

    public static Predicator<String> match(String regex) {
        return t -> Pattern.matches(regex, t);
    }

    public static Predicator<String> notMatch(String regex) {
        return Predicator.not(match(regex));
    }
}
