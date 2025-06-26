package com.solid.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.awt.print.Book;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String name;

    @OneToOne
    private SeatType seatType;

    private int rolVal;
    private int colval;
}
