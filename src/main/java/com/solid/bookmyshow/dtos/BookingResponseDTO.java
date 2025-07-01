package com.solid.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDTO {

    private Long bookingId;
    private ResponseStatus responseStatus;
    private int amount;
    private String failureMessage;

}
