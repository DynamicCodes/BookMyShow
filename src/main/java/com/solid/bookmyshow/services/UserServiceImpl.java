package com.solid.bookmyshow.services;

import com.solid.bookmyshow.models.User;
import com.solid.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    /*
     * This is a bad way of implementing/integrating bcryptpasswordencoder.
     * Better way would be to inject and create a bean.
     * Since 'Bean' definitions would be covered in project module, for now we go with this version
     * */
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();
        if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            return user;
        }
        throw new RuntimeException("Wrong password");
    }

    @Override
    public User register(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }
}
