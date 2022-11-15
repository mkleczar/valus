package org.mk.valus.validators;

import org.mk.valus.Validator;
import org.mk.valus.predicators.IntegerPredicators;

public class IntegerValidators {
    public static Validator<Integer> isGreaterThen(int nr) {
        return Validator.from(IntegerPredicators.isGreaterThan(nr), i -> String.format("Int value is '%d', but value greater than %d was expected", i, nr));
    }
}
