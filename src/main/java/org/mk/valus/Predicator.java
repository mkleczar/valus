package org.mk.valus;

import java.util.function.Predicate;

public interface Predicator<T> {

    boolean isValid(T t);

    static <T> Predicator<T> from(Predicate<T> predicate) {
        return predicate::test;
    }

    static <T> Predicator<T> not(Predicator<T> predicator) {
        return t -> !predicator.isValid(t);
    }

    static <T> Predicator<T> and(Predicator<T> v1, Predicator<T> v2) {
        return t -> v1.isValid(t) && v2.isValid(t);
    }


}
