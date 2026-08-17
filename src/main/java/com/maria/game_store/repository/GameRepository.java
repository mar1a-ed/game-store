package com.maria.game_store.repository;

import com.maria.game_store.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByGenreContaining(String genre);

    List<Game> findByTitleContaining(String title);
}
