package com.solid.bookmyshow.services;

import com.solid.bookmyshow.models.*;
import com.solid.bookmyshow.repositories.BookingRepository;
import com.solid.bookmyshow.repositories.ShowRepository;
import com.solid.bookmyshow.repositories.ShowSeatRepository;
import com.solid.bookmyshow.repositories.UserRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final PriceCalculatorService priceCalculatorService;
    BookingRepository bookingRepository;
    ShowRepository showRepository;
    ShowSeatRepository showSeatRepository;
    ShowSeatRepository showSeatSeatRepository;
    UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, ShowRepository showRepository,
                              ShowSeatRepository showSeatRepository, UserRepository userRepository, PriceCalculatorService priceCalculatorService) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.priceCalculatorService = priceCalculatorService;
    }



    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE) // Use SERIALIZABLE isolation level to prevent dirty reads and ensure consistency
    public Booking bookMovie(List<Long> showSeatIds, Long userId, Long showId) {
       //1. get the show details using the showId
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new RuntimeException("Show not found");
        }
        Show show = showOptional.get();

        //2. get the user details using the userId
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();

        //take the lock (actual scenario for locking)
        //3. get the showseats using the showSeatIds
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        //4. check if the showSeats are available
        for(ShowSeat showSeat : showSeats){
            if((!showSeat.getShow().equals(ShowSeatStatus.AVAILABLE))){
                if(Duration.between(showSeat.getBlockedAt().toInstant(),
                        new Date().toInstant()).toMinutes() > 15) {
                    // if the show seat is blocked for more than 15 minutes, then we can consider it as available
//                    showSeat.setStatus(ShowSeatStatus.AVAILABLE);
//                    showSeat.setBlockedAt(null);
                    throw new RuntimeException("Show seat should be released");
                }
                //5. if not available, throw an exception
                throw new RuntimeException("Show Seat Not Available");
            }
        }
        //6. if yes change the status of the showSeats to BOOKED/locked/blocked
        for(ShowSeat showSeat : showSeats){
            showSeat.setStatus(ShowSeatStatus.BOOKED);
            showSeat.setBlockedAt(new Date());
            //7. save the details in the db
            showSeatRepository.save(showSeat);
        }

        //release the lock (actual scenario for locking)
        //8. create a booking object
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookedAt(new Date());
        booking.setShowSeats(showSeats);
        booking.setStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculatorService.calculateAmount(showSeats, show));

        return bookingRepository.save(booking);
    }
}
