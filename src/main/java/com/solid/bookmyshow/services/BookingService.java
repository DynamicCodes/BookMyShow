package com.solid.bookmyshow.services;

import com.solid.bookmyshow.models.Booking;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookingService {

    Booking bookMovie(List<Long> showSeatIds, Long userId, Long showId);
}
