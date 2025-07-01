package com.solid.bookmyshow;

import com.solid.bookmyshow.controllers.UserController;
import com.solid.bookmyshow.dtos.signUpRequestDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BookMyShowApplication implements CommandLineRunner {

    UserController userController;

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        signUpRequestDto signupRequestDto = new signUpRequestDto();
        signupRequestDto.setEmail("email");
        signupRequestDto.setPassword("password");

        userController.registerUser(signupRequestDto);

        userController.login("email", "password");
    }
}
