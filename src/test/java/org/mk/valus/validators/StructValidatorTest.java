package org.mk.valus.validators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mk.valus.StructValidator;
import org.mk.valus.ValidationError;

import java.util.List;

@Slf4j
public class StructValidatorTest {

    @Test
    public void userValidationTest() {
        Address address = new Address("Długa", "33-333");
        User u = new User("John", "Doe", 25, address);

        StructValidator<Address> vAddress = StructValidator.from("postcode", Address::getPostcode, StringValidators.isNull());

        StructValidator<User> v = StructValidator.all(
                StructValidator.from("firstName", User::getFirstName, StringValidators.isNull()),
                StructValidator.from("age", User::getAge, IntegerValidators.isGreaterThen(30)),
                StructValidator.sub("address", User::getAddress, vAddress)
        );
        List<ValidationError> errorList = v.validate(u);
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
}
