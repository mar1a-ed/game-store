package com.maria.game_store.repository;

import com.maria.game_store.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByCodeRh(String codeRh);
}
