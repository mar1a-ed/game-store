package com.maria.game_store.dto;

import com.maria.game_store.model.entity.Admin;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AdminMapper {

    public static Admin toAdmin(AdminResponseDTO dto){
        return new ModelMapper().map(dto, Admin.class);
    }

    public static AdminResponseDTO toDto(Admin admin){
        return new ModelMapper().map(admin, AdminResponseDTO.class);
    }
}
