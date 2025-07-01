package com.solid.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class signUpResponseDto {
    private ResponseStatus status;
    private long userId;
    private String failureMessage;
}
