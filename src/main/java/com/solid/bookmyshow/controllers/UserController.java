package com.solid.bookmyshow.controllers;

import com.solid.bookmyshow.dtos.ResponseStatus;
import com.solid.bookmyshow.dtos.signUpRequestDto;
import com.solid.bookmyshow.dtos.signUpResponseDto;
import com.solid.bookmyshow.models.User;
import com.solid.bookmyshow.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    public void login(String email, String password){
        User user = userService.login(email, password);
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());

    }
    public signUpResponseDto registerUser(signUpRequestDto signUpRequestDto) {
        signUpResponseDto response = new signUpResponseDto();
        try {
            User user = userService.register(signUpRequestDto.getEmail(),
                    signUpRequestDto.getPassword());
            response.setStatus(ResponseStatus.SUCCESS);
            response.setUserId(user.getId());
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setFailureMessage(e.getMessage());
        }
        return response;
    }
}
