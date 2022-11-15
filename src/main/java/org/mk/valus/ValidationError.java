package org.mk.valus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class ValidationError {
    private int code;
    private String forField;
    private String description;
}
