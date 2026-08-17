package com.maria.game_store.service;

import com.maria.game_store.dto.GameInsertDTO;
import com.maria.game_store.dto.GameMapper;
import com.maria.game_store.exception.GameNotFoundException;
import com.maria.game_store.model.entity.Game;
import com.maria.game_store.model.enums.Genre;
import com.maria.game_store.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    public Game insertGame(GameInsertDTO gameInsertDTO){
        Game game = GameMapper.toGame(gameInsertDTO);

        try{
            gameRepository.save(game);
        }catch (Exception e){
            throw new RuntimeException("Request with not success");
        }

        return game;
    }

    public Game findGameById(Long id){
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new GameNotFoundException("Game not found.")
        );

        return game;
    }

    public List<Game> findGameByTitle(String title){
        List<Game> games = gameRepository.findByTitleContaining(title);

        if(games.isEmpty()){
            throw new GameNotFoundException("Game not found.");
        }

        return games;
    }

    public List<Game> findByGenre(String genre){
        List<Game> games = gameRepository.findByGenreContaining(genre);

        if(games.isEmpty()){
            throw new GameNotFoundException("Game not found.");
        }

        return games;
    }

    public void updateGame(Long id, String option, Game data){
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new GameNotFoundException("Game not found.")
        );

        if(option.equalsIgnoreCase("studio")){
            game.setStudio(data.getStudio());
        }else if(option.equalsIgnoreCase("description")){
            game.setDescription(data.getDescription());
        }else if(option.equalsIgnoreCase("genre")){
            game.setGenre(data.getGenre());
        }else if(option.equalsIgnoreCase("stockQuantity")){
            game.setStockQuantity(data.getStockQuantity());
        }else if(option.equalsIgnoreCase("price")){
            game.setPrice(data.getPrice());
        }else{
            game.setAgeRating(data.getAgeRating());
        }

        gameRepository.save(game);
    }
}
