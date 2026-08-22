package com.maria.game_store.service;

import com.maria.game_store.dto.AdminCreateDTO;
import com.maria.game_store.exception.NicknameException;
import com.maria.game_store.exception.RoleException;
import com.maria.game_store.exception.UserNotFoundException;
import com.maria.game_store.model.entity.Admin;
import com.maria.game_store.model.entity.Client;
import com.maria.game_store.model.entity.User;
import com.maria.game_store.model.enums.Position;
import com.maria.game_store.model.enums.Role;
import com.maria.game_store.repository.AdminRepository;
import com.maria.game_store.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final UserRepository userRepository;

    @Transactional
    public Admin createAdmin(AdminCreateDTO dto){
        Admin admin = new Admin();

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Error. User already exists.");
        }

        if(adminRepository.existsByCodeRh(dto.getCodeRh())){
            throw new RuntimeException("Error. Admin already exists.");
        }

        if(userRepository.existsByNickname(dto.getNickname())){
            throw new NicknameException("Nickname already exists.");
        }

        admin.setName(dto.getName());
        admin.setNickname(dto.getNickname());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setCodeRh(dto.getCodeRh());
        admin.setRole(Role.ROLE_ADMIN);
        admin.setPosition(Position.valueOf(dto.getPosition()));

        adminRepository.save(admin);

        return admin;
    }

    @SneakyThrows
    @Transactional
    public Admin updatePositionMid(Long id){
        Admin admin = (Admin) userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found.")
        );

        if(!admin.getRole().equals(Role.ROLE_ADMIN)){
            throw new RoleException("The user is not a admin.");
        }

        admin.setPosition(Position.MID_LEVEL);
        adminRepository.save(admin);

        return admin;
    }

    @SneakyThrows
    @Transactional
    public Admin updatePositionSenior(Long id){
        Admin admin = (Admin) userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found.")
        );

        if(!admin.getRole().equals(Role.ROLE_ADMIN)){
            throw new RoleException("The user is not a admin.");
        }

        admin.setPosition(Position.SENIOR_LEVEL);
        adminRepository.save(admin);

        return admin;
    }
}
