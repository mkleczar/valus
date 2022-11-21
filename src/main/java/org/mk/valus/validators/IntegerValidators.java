package org.mk.valus.validators;

import org.mk.valus.Validator;
import org.mk.valus.predicators.IntegerPredicators;

public class IntegerValidators {
    public static Validator<Integer> isGreaterThen(int nr) {
        return Validator.from(
                IntegerPredicators.isGreaterThan(nr),
                i -> String.format("Int value is '%d', but value greater than %d was expected", i, nr),
                ErrorCodes.IS_LESS_OR_EQUAL);
    }

    public static Validator<Integer> isLessThen(int nr) {
        return Validator.from(
                IntegerPredicators.isLessThan(nr),
                i -> String.format("Int value is '%d', but value less than %d was expected", i, nr),
                ErrorCodes.IS_GREATER_OR_EQUAL);
    }
}
