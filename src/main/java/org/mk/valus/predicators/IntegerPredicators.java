package org.mk.valus.predicators;

import org.mk.valus.Predicator;

import java.util.Objects;

public class IntegerPredicators {

    public static Predicator<Integer> isGreaterThan(int nr) {
        return i -> i > nr;
    }

    public static Predicator<Integer> isLessThan(int nr) {
        return i -> i < nr;
    }
}
