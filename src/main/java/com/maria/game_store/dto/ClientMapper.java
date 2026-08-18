package com.maria.game_store.dto;

import com.maria.game_store.model.entity.Client;
import org.modelmapper.ModelMapper;

public class ClientMapper {

    public static ClientResponseDTO toDto(Client client){
        return new ModelMapper().map(client, ClientResponseDTO.class);
    }

    public static Client toClient(ClientResponseDTO dto){
        return new ModelMapper().map(dto, Client.class);
    }

    public static Client toClientCreate(ClientCreateDTO dto){
        return new ModelMapper().map(dto, Client.class);
    }
}
