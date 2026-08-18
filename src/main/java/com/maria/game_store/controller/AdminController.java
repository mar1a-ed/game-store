package com.maria.game_store.controller;

import com.maria.game_store.dto.AdminCreateDTO;
import com.maria.game_store.dto.AdminMapper;
import com.maria.game_store.dto.AdminResponseDTO;
import com.maria.game_store.model.entity.Admin;
import com.maria.game_store.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<AdminResponseDTO> createAdmin(@RequestBody AdminCreateDTO dto){
        try{
            Admin admin = adminService.createAdmin(dto);

            return ResponseEntity.ok().body(AdminMapper.toDto(admin));
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Error. Illegal argument insert.");
        }catch (Exception e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
