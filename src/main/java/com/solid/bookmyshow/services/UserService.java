package com.solid.bookmyshow.services;

import com.solid.bookmyshow.models.User;

public interface UserService {
    User login(String email, String password);
    User register(String email, String password);
}
