package com.maria.game_store.repository;

import com.maria.game_store.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    T findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    T findByNickname(String nickname);
}
