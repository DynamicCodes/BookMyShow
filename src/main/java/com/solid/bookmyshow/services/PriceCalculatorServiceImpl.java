package com.solid.bookmyshow.services;

import com.solid.bookmyshow.models.Show;
import com.solid.bookmyshow.models.ShowSeat;
import com.solid.bookmyshow.models.ShowSeatType;
import com.solid.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorServiceImpl implements PriceCalculatorService {

    ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorServiceImpl(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    @Override
    public double calculateAmount(List<ShowSeat> showSeats, Show show) {
        double amount = 0;
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
        for(ShowSeat showSeat : showSeats) {
            for(ShowSeatType showSeatType : showSeatTypes) {
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                }
            }
        }
        return amount;

    }
}
