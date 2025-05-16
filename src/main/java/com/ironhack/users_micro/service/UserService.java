package com.ironhack.users_micro.service;

import com.ironhack.users_micro.exception.UserNotFoundException;
import com.ironhack.users_micro.model.User;
import com.ironhack.users_micro.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User patchAccountId(Long userId, Long accountId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User foundUser;
        if (optionalUser.isPresent()) {
            foundUser = optionalUser.get();

            foundUser.setAccountID(accountId);

            return  foundUser;

        }else{
            throw new UserNotFoundException("Couldn't find the user");
        }

    }
}
