package org.mk.valus.validators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mk.valus.StructValidator;
import org.mk.valus.ValidatorStructError;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StructValidatorTest {

    @Test
    public void userValidationTest() {
        Address address = new Address("Długa", "33-3331");
        User u = new User("John", "Doe", 25, address);

        StructValidator<Address> vAddress = StructValidator.all(
                StructValidator.from("postcode", Address::getPostcode, StringValidators.isPostcode()),
                StructValidator.from("street", Address::getStreet, StringValidators.isNull())
        );

        StructValidator<User> v = StructValidator.all(
                StructValidator.from("firstName", User::getFirstName, StringValidators.isNull()),
                StructValidator.from("age", User::getAge, IntegerValidators.isGreaterThen(30)),
                StructValidator.sub("address", User::getAddress, vAddress)
        );
        List<ValidatorStructError> errorList = v.validate(u);
        log.info("{}", errorList);
    }


    @Getter
    @AllArgsConstructor
    static class User {
        private String firstName;
        private String secondName;
        private int age;
        private Address address;
    }
    @Getter
    @AllArgsConstructor
    static class Address {
        private String street;
        private String postcode;
    }

    @Test
    public void anyValidatorTest() {
        Address address = new Address(null, null);
        StructValidator<Address> vAddress = StructValidator.any(
                StructValidator.from("postcode", Address::getPostcode, StringValidators.isNotNull()),
                StructValidator.from("street", Address::getStreet, StringValidators.isNotNull())
        );
        List<ValidatorStructError> errorList = vAddress.validate(address);
        assertThat(errorList).hasSize(1)
                .element(0).extracting(ValidatorStructError::getForField).isEqualTo("postcode");
    }
}
