package com.solid.bookmyshow.services;

import com.solid.bookmyshow.models.Show;
import com.solid.bookmyshow.models.ShowSeat;

import java.util.List;

public interface PriceCalculatorService {
    double calculateAmount(List<ShowSeat> showSeats, Show show);
}
