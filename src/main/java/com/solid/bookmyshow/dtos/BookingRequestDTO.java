package com.solid.bookmyshow.dtos;

import com.solid.bookmyshow.models.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequestDTO {

    private List<Long> showSeatIds;
    private Long userId;
    private Long showId;
}
