package com.maria.game_store.dto;

import com.maria.game_store.model.entity.User;
import org.modelmapper.ModelMapper;

public class UserMapper {

    public static UserResponseDTO toDto(User user){
        return new ModelMapper().map(user, UserResponseDTO.class);
    }

    public static User toUser(UserResponseDTO dto){
        return new ModelMapper().map(dto, User.class);
    }
}
