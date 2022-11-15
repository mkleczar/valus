package org.mk.valus.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mk.valus.Predicator;
import org.mk.valus.predicators.StringPredicators;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StringPredicatorTest {

    @ParameterizedTest
    @MethodSource("stringValueTestData")
    public void simpleStringValidatorsTest(String str, Predicator<String> predicator, boolean expected) {
        assertThat(predicator.isValid(str)).isEqualTo(expected);
    }

    private static Stream<Arguments> stringValueTestData() {
        return Stream.<Arguments>builder()
                .add(Arguments.of(null, StringPredicators.isNull(), true))
                .add(Arguments.of(null, StringPredicators.isNotNull(), false))
                .add(Arguments.of(null, StringPredicators.isEmpty(), false))
                .add(Arguments.of(null, StringPredicators.isNotEmpty(), false))
                .add(Arguments.of("", StringPredicators.isNull(), false))
                .add(Arguments.of("", StringPredicators.isNotNull(), true))
                .add(Arguments.of("", StringPredicators.isEmpty(), true))
                .add(Arguments.of("", StringPredicators.isNotEmpty(), false))
                .add(Arguments.of("a", StringPredicators.isNull(), false))
                .add(Arguments.of("a", StringPredicators.isNotNull(), true))
                .add(Arguments.of("a", StringPredicators.isEmpty(), false))
                .add(Arguments.of("a", StringPredicators.isNotEmpty(), true))
                .build();
    }


    @ParameterizedTest
    @MethodSource("stringRegexMatchValueTestData")
    public void stringRegexMatchValidatorsTest(String str, Predicator<String> predicator, boolean expected) {
        assertThat(predicator.isValid(str)).isEqualTo(expected);
    }

    private static Stream<Arguments> stringRegexMatchValueTestData() {
        return Stream.<Arguments>builder()
                .add(Arguments.of("ala", StringPredicators.match("ala"), true))
                .add(Arguments.of("ala", StringPredicators.match("al."), true))
                .add(Arguments.of("ala", StringPredicators.match("a.a"), true))
                .add(Arguments.of("ala", StringPredicators.match("[al]+"), true))
                .add(Arguments.of("ala", StringPredicators.match("[ax]+"), false))
                .add(Arguments.of("ala", StringPredicators.notMatch("[ax]+"), true))
                .build();
    }
}
