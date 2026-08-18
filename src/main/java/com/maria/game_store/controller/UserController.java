package com.maria.game_store.controller;

import com.maria.game_store.dto.UserMapper;
import com.maria.game_store.dto.UserResponseDTO;
import com.maria.game_store.model.entity.User;
import com.maria.game_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
        User user = userService.findById(id);
        UserResponseDTO dto = UserMapper.toDto(user);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String nickname){
        User user = userService.findByNickname(nickname);
        UserResponseDTO dto = UserMapper.toDto(user);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(@PathVariable String email){
        User user = userService.findByEmail(email);
        UserResponseDTO dto = UserMapper.toDto(user);

        return ResponseEntity.ok().body(dto);
    }
}
