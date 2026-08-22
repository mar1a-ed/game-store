package com.maria.game_store.service;

import com.maria.game_store.dto.client.ClientCreateDTO;
import com.maria.game_store.dto.user.UserUpdateDTO;
import com.maria.game_store.exception.ResourceInUseException;
import com.maria.game_store.model.entity.Client;
import com.maria.game_store.model.enums.Role;
import com.maria.game_store.repository.ClientRepository;
import com.maria.game_store.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final UserRepository userRepository;

    @Transactional
    public Client createClient(ClientCreateDTO dto){
        Client client = new Client();

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new ResourceInUseException("User already exists.");
        }

        if(clientRepository.existsByCpf(dto.getCpf())){
            throw new ResourceInUseException("Client already exists.");
        }

        if(userRepository.existsByNickname(dto.getNickname())){
            throw new ResourceInUseException("Nickname already exists.");
        }

        client.setName(dto.getName());
        client.setNickname(dto.getNickname());
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
        client.setCpf(dto.getCpf());
        client.setBirthday(dto.getBirthday());
        client.setRole(Role.ROLE_CLIENT);

        clientRepository.save(client);

        return client;
    }

}
