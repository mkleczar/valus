package org.mk.valus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationError {
    private int code;
    private String value;
    private String description;
}
