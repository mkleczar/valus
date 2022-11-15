package org.mk.valus;

import org.mk.valus.validators.ErrorCodes;

import java.util.Optional;
import java.util.function.Function;

public interface Validator<T> {
    Optional<ValidatorError> validate(T t);

    static <T> Validator<T> from(Predicator<T> basePredicator, Function<T, String> errorDescription, int errorCode) {
        return t ->
            basePredicator.isValid(t) ?
                    Optional.empty() :
                    Optional.of(
                            ValidatorError.builder()
                                    .code(errorCode)
                                    .description(errorDescription.apply(t))
                                    .build());
    }
    static <T> Validator<T> from(Predicator<T> basePredicator, Function<T, String> errorDescription, ErrorCodes errorCode) {
        return from(basePredicator, errorDescription, errorCode.getCode());
    }
}
