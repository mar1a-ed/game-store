package com.maria.game_store.service;

import com.maria.game_store.dto.game.GameInsertDTO;
import com.maria.game_store.dto.mapper.GameMapper;
import com.maria.game_store.exception.GameNotFoundException;
import com.maria.game_store.exception.NegativeQuantityException;
import com.maria.game_store.exception.ZeroInventoryException;
import com.maria.game_store.model.entity.Game;
import com.maria.game_store.model.enums.GameStockVariationUpdate;
import com.maria.game_store.model.enums.GameUpdateOption;
import com.maria.game_store.repository.GameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    @Transactional
    public Game insertGame(GameInsertDTO gameInsertDTO){
        Game game = GameMapper.toGame(gameInsertDTO);

        gameRepository.save(game);

        return game;

    }

    @Transactional
    public Game findGameById(Long id){
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new GameNotFoundException("Game not found.")
        );

        return game;

    }

    @Transactional
    public List<Game> findGameByTitle(String title){
        List<Game> games = gameRepository.findByTitleContaining(title);

        if (games.isEmpty()) {
            throw new GameNotFoundException("Game not found.");
        }

        return games;

    }

    @Transactional
    public List<Game> findByGameGenre(String genre){
        List<Game> games = gameRepository.findByGenreContaining(genre);

        if (games.isEmpty()) {
            throw new GameNotFoundException("Game not found.");
        }

        return games;

    }

    @Transactional
    public void updateGame(Long id, GameUpdateOption option, Game data){
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new GameNotFoundException("Game not found.")
        );

        switch (option){
            case STUDIO -> game.setStudio(data.getStudio());
            case DESCRIPTION -> game.setDescription(data.getDescription());
            case GENRE -> game.setGenre(data.getGenre());
            case PRICE -> game.setPrice(data.getPrice());
            case AGE_RATING -> game.setAgeRating(data.getAgeRating());
        }
    }

    @Transactional
    public void updateGameStock(Long id, GameStockVariationUpdate variation, Integer quantity){
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new GameNotFoundException("Game not found.")
        );

        if(quantity < 0){
            throw new NegativeQuantityException("Insert a valid quantity.");
        }

        switch (variation){
            case INLET -> game.setStockQuantity(game.getStockQuantity() + quantity);
            case OUTLET -> game.setStockQuantity(game.getStockQuantity() - quantity);
        }

    }

    @Transactional
    public Game gameOutOfStock(Long id){
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new GameNotFoundException("Game not found.")
        );

        if(game.getStockQuantity() == 0){
            throw new ZeroInventoryException("Game is already out of stock.");
        }

        game.setStockQuantity(0);

        return game;
    }
}
