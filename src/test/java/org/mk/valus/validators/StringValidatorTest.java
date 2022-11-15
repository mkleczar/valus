package org.mk.valus.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mk.valus.Validator;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StringValidatorTest {

    @ParameterizedTest
    @MethodSource("stringValueTestData")
    public void simpleStringValidatorsTest(String str, Validator<String> validator, boolean expected) {
        assertThat(validator.isValid(str)).isEqualTo(expected);
    }

    private static Stream<Arguments> stringValueTestData() {
        return Stream.<Arguments>builder()
                .add(Arguments.of(null, StringValidators.isNull(), true))
                .add(Arguments.of(null, StringValidators.isNotNull(), false))
                .add(Arguments.of(null, StringValidators.isEmpty(), false))
                .add(Arguments.of(null, StringValidators.isNotEmpty(), false))
                .add(Arguments.of("", StringValidators.isNull(), false))
                .add(Arguments.of("", StringValidators.isNotNull(), true))
                .add(Arguments.of("", StringValidators.isEmpty(), true))
                .add(Arguments.of("", StringValidators.isNotEmpty(), false))
                .add(Arguments.of("a", StringValidators.isNull(), false))
                .add(Arguments.of("a", StringValidators.isNotNull(), true))
                .add(Arguments.of("a", StringValidators.isEmpty(), false))
                .add(Arguments.of("a", StringValidators.isNotEmpty(), true))
                .build();
    }


    @ParameterizedTest
    @MethodSource("stringRegexMatchValueTestData")
    public void stringRegexMatchValidatorsTest(String str, Validator<String> validator, boolean expected) {
        assertThat(validator.isValid(str)).isEqualTo(expected);
    }

    private static Stream<Arguments> stringRegexMatchValueTestData() {
        return Stream.<Arguments>builder()
                .add(Arguments.of("ala", StringValidators.match("ala"), true))
                .add(Arguments.of("ala", StringValidators.match("al."), true))
                .add(Arguments.of("ala", StringValidators.match("a.a"), true))
                .add(Arguments.of("ala", StringValidators.match("[al]+"), true))
                .add(Arguments.of("ala", StringValidators.match("[ax]+"), false))
                .add(Arguments.of("ala", StringValidators.notMatch("[ax]+"), true))
                .build();
    }
}
