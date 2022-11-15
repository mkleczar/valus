package org.mk.valus;

import java.util.function.Predicate;

public interface Validator<T> {

    boolean isValid(T t);

    static <T> Validator<T> from(Predicate<T> predicate) {
        return predicate::test;
    }

    static <T> Validator<T> not(Validator<T> validator) {
        return t -> !validator.isValid(t);
    }

    static <T> Validator<T> and(Validator<T> v1, Validator<T> v2) {
        return t -> v1.isValid(t) && v2.isValid(t);
    }


}
