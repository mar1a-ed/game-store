package com.maria.game_store.controller;

import com.maria.game_store.dto.UserMapper;
import com.maria.game_store.dto.UserResponseDTO;
import com.maria.game_store.dto.UserUpdateDTO;
import com.maria.game_store.model.entity.User;
import com.maria.game_store.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{nickname}/update")
    public ResponseEntity<UserResponseDTO> updateUserData(@PathVariable String nickname, @RequestBody @Valid UserUpdateDTO data){
        User user = userService.updateUserData(nickname, data);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
