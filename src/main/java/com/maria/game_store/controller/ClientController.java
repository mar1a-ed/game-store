package com.maria.game_store.controller;

import com.maria.game_store.dto.ClientCreateDTO;
import com.maria.game_store.dto.ClientMapper;
import com.maria.game_store.dto.ClientResponseDTO;
import com.maria.game_store.model.entity.Client;
import com.maria.game_store.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody ClientCreateDTO dto){
        try{
            Client client = clientService.createClient(dto);

            if(client == null){
                throw new RuntimeException("Client is null.");
            }

            return ResponseEntity.ok().body(ClientMapper.toDto(client));
        }catch (IllegalArgumentException e) {
            throw new RuntimeException("Error. Illegal argument insert.");
        }
    }

}
