package com.maria.game_store.repository;

import com.maria.game_store.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends UserRepository<Client> {
    boolean existsByCpf(String cpf);
}
