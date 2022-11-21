package org.mk.valus;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface StructValidator<T> {

    List<ValidatorStructError> validate(T t);

    static <T,R> StructValidator<T> from(String fieldName, Function<T,R> getter, Validator<R> validator) {
        return t ->
                validator.validate(getter.apply(t))
                        .map(validatorError -> List.of(ValidatorStructError.builder()
                                .code(validatorError.getCode())
                                .forField(fieldName)
                                .description(validatorError.getDescription())
                                .build()))
                        .orElse(Collections.emptyList());
    }

    static <T,R> StructValidator<T> sub(String fieldName, Function<T,R> getter, StructValidator<R> subValidator) {
        return new SubValidator<>(fieldName, getter, subValidator);
    }

    @SafeVarargs
    static <T> StructValidator<T> all(StructValidator<T> ... vals) {
        return t -> Arrays.stream(vals)
                .map(v -> v.validate(t))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    // test to first error
    @SafeVarargs
    static <T> StructValidator<T> any(StructValidator<T> ... vals) {
        return t -> Arrays.stream(vals)
                .map(v -> v.validate(t))
                .filter(list -> !list.isEmpty())
                .findAny()
                .orElse(Collections.emptyList());
    }

    @AllArgsConstructor
    static class SubValidator<T,R> implements StructValidator<T> {

        private String fieldName;
        private Function<T,R> getter;
        private StructValidator<R> subValidator;

        @Override
        public List<ValidatorStructError> validate(T t) {
            return subValidator.validate(getter.apply(t))
                    .stream()
                    .map(ve -> ValidatorStructError
                            .builder()
                            .code(ve.getCode())
                            .forField(fieldName + "->" + ve.getForField())
                            .description(ve.getDescription())
                            .build())
                    .collect(Collectors.toList());
        }
    }
}