package com.maria.game_store.controller;

import com.maria.game_store.dto.AdminCreateDTO;
import com.maria.game_store.dto.AdminMapper;
import com.maria.game_store.dto.AdminResponseDTO;
import com.maria.game_store.model.entity.Admin;
import com.maria.game_store.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{id}/update/position/mid")
    public ResponseEntity<AdminResponseDTO> updatePositionMid(@PathVariable Long id){
        Admin admin = adminService.updatePositionMid(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(AdminMapper.toDto(admin));
    }

    @PatchMapping("/{id}/update/position/senior")
    public ResponseEntity<AdminResponseDTO> updatePositionSenior(@PathVariable Long id){
        Admin admin = adminService.updatePositionSenior(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(AdminMapper.toDto(admin));
    }
}
