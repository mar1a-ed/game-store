package com.maria.game_store.service;

import com.maria.game_store.exception.UserNotFoundException;
import com.maria.game_store.model.entity.User;
import com.maria.game_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found.")
        );

        return user;
    }

    public User findByEmail(String email){
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("User not found.");
        }

        return user;
    }

    public User findByNickname(String nickname){
        User user = findByNickname(nickname);

        if(user == null){
            throw new UserNotFoundException("User not found.");
        }

        return user;
    }
}
