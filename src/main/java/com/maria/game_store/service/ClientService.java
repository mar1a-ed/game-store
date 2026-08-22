package com.maria.game_store.service;

import com.maria.game_store.dto.ClientCreateDTO;
import com.maria.game_store.exception.NicknameException;
import com.maria.game_store.model.entity.Client;
import com.maria.game_store.model.entity.User;
import com.maria.game_store.model.enums.Role;
import com.maria.game_store.repository.ClientRepository;
import com.maria.game_store.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.maria.game_store.service.UserService;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final UserRepository userRepository;

    @Transactional
    public Client createClient(ClientCreateDTO dto){
        Client client = new Client();

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("User already exists.");
        }

        if(clientRepository.existsByCpf(dto.getCpf())){
            throw new RuntimeException("Client already exists.");
        }

        if(userRepository.existsByNickname(dto.getNickname())){
            throw new NicknameException("Nickname already exists.");
        }

        client.setName(dto.getName());
        client.setNickname(dto.getNickname());
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
        client.setCpf(dto.getCpf());
        client.setBirthday(dto.getBirthday());
        client.setRole(Role.ROLE_CLIENT);

        return client;
    }

}
