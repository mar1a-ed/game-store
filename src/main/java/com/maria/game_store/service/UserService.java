package com.maria.game_store.service;

import com.maria.game_store.dto.user.UserUpdateDTO;
import com.maria.game_store.exception.NicknameException;
import com.maria.game_store.exception.UserNotFoundException;
import com.maria.game_store.model.entity.User;
import com.maria.game_store.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService<T extends User> {

    private final UserRepository userRepository;

    @SneakyThrows
    @Transactional
    public User findById(Long id){
        User user = (User) userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found.")
        );

        return user;
    }

    @Transactional
    public User findByEmail(String email){
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserNotFoundException("User not found.");
        }

        return user;
    }

    @Transactional
    public User findByNickname(String nickname){
        User user = userRepository.findByNickname(nickname);

        if(user == null){
            throw new UserNotFoundException("User not found.");
        }

        return user;
    }

    @Transactional
    public T updateUserData(String nickname, UserUpdateDTO dto){
        User user = userRepository.findByNickname(nickname);

        if(user == null){
            throw new UserNotFoundException("User not found.");
        }

        if(nickname.equals(dto.getNickname())){
            throw new NicknameException("Nickname already in use.");
        }

        user.setNickname(dto.getNickname());
        user.setName(dto.getName());

        return (T) userRepository.save(user);
    }

}










