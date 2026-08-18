package com.maria.game_store.service;

import com.maria.game_store.dto.ClientCreateDTO;
import com.maria.game_store.dto.ClientMapper;
import com.maria.game_store.model.entity.Client;
import com.maria.game_store.model.enums.Role;
import com.maria.game_store.repository.ClientRepository;
import com.maria.game_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final UserRepository userRepository;

    public Client createClient(ClientCreateDTO dto){
        Client client = new Client();

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("User already exists.");
        }

        if(clientRepository.existsByCpf(dto.getCpf())){
            throw new RuntimeException("Client already exists.");
        }

        client.setNickname(dto.getNickname());
        client.setPassword(dto.getPassword());
        client.setEmail(dto.getEmail());
        client.setCpf(dto.getCpf());
        client.setRole(Role.ROLE_CLIENT);
        clientRepository.save(client);

        return client;
    }
}
