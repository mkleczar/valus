package org.mk.valus;

import java.util.Optional;
import java.util.function.Function;

public interface Validator<T> {
    Optional<String> validate(T t);

    static <T> Validator<T> from(Predicator<T> basePredicator, Function<T, String> errorDescription) {
        return t ->
            basePredicator.isValid(t) ?
                    Optional.empty() :
                    Optional.of(errorDescription.apply(t));
    }
}
