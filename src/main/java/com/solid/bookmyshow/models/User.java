package com.solid.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel{

    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    @OneToMany
    private List<Booking> bookings;
}
