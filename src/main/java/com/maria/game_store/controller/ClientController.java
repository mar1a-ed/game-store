package com.maria.game_store.controller;

import com.maria.game_store.dto.client.ClientCreateDTO;
import com.maria.game_store.dto.mapper.ClientMapper;
import com.maria.game_store.model.entity.Client;
import com.maria.game_store.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody @Valid ClientCreateDTO dto){
        Client client = clientService.createClient(dto);

        if(client == null){
            throw new RuntimeException("Client is null.");
        }

        return ResponseEntity.ok().body(ClientMapper.toDto(client));

    }

}
