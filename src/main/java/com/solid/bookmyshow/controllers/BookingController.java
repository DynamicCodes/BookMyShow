package com.solid.bookmyshow.controllers;

import com.solid.bookmyshow.dtos.BookingRequestDTO;
import com.solid.bookmyshow.dtos.BookingResponseDTO;
import com.solid.bookmyshow.dtos.ResponseStatus;
import com.solid.bookmyshow.models.Booking;
import com.solid.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingResponseDTO bookTickets(BookingRequestDTO bookingRequestDTO){
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

        try{
            Booking booking = bookingService.bookMovie(
                bookingRequestDTO.getShowSeatIds(),
                bookingRequestDTO.getUserId(),
                bookingRequestDTO.getShowId()
            );
            bookingResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            bookingResponseDTO.setBookingId(booking.getId());
        }catch (Exception e){
            bookingResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
            bookingResponseDTO.setFailureMessage(e.getMessage());
        }


        return bookingResponseDTO;
    }
}
