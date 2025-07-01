package com.solid.bookmyshow.repositories;

import com.solid.bookmyshow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Override
    Booking save(Booking entity);
}
