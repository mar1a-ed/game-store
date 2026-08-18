package com.maria.game_store.service;

import com.maria.game_store.dto.AdminCreateDTO;
import com.maria.game_store.model.entity.Admin;
import com.maria.game_store.model.enums.Role;
import com.maria.game_store.repository.AdminRepository;
import com.maria.game_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final UserRepository userRepository;

    public Admin createAdmin(AdminCreateDTO dto){
        Admin admin = new Admin();

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Error. User already exists.");
        }

        if(adminRepository.existsByCodeRh(dto.getCodeRh())){
            throw new RuntimeException("Error. Admin already exists.");
        }

        admin.setNickname(dto.getNickname());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setCodeRh(dto.getCodeRh());
        admin.setRole(Role.ROLE_ADMIN);

        adminRepository.save(admin);

        return admin;
    }
}
