package com.maria.game_store.controller;

import com.maria.game_store.dto.GameMapper;
import com.maria.game_store.dto.GameResponseDTO;
import com.maria.game_store.model.entity.Game;
import com.maria.game_store.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameService gameService;

    @PostMapping("/insert")
    public ResponseEntity<GameResponseDTO> insertGame(@RequestBody Game game){
        gameService.insertGame(GameMapper.toGameDto(game));
        return ResponseEntity.ok().body(GameMapper.toDto(game));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findGameById(@PathVariable Long id){
        Game game = gameService.findGameById(id);

        return ResponseEntity.ok().body(GameMapper.toDto(game));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<GameResponseDTO>> findGameByTitle(@PathVariable String title){
        List<Game> games = gameService.findGameByTitle(title);

        List<GameResponseDTO> gamesDto = new ArrayList<>();

        games.forEach(game -> gamesDto.add(GameMapper.toDto(game)));

        return ResponseEntity.ok().body(gamesDto);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<GameResponseDTO>> findGameByGenre(@PathVariable String genre){
        List<Game> games = gameService.findGameByTitle(genre);

        List<GameResponseDTO> gamesDto = new ArrayList<>();

        games.forEach(game -> gamesDto.add(GameMapper.toDto(game)));

        return ResponseEntity.ok().body(gamesDto);
    }

    @PatchMapping("/{id}/update/{option}")
    public ResponseEntity<Void> updateGame(@PathVariable Long id, @PathVariable String option, @RequestBody Game data){
        gameService.updateGame(id, option, data);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PatchMapping("/{id}/update/stock/{variation}")
    public ResponseEntity<Void> updateGameStock(@PathVariable Long id, @PathVariable String variation, @RequestBody Integer quantity){
        gameService.updateGameStock(id, variation, quantity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PatchMapping("/{id}/update/stock-out")
    public ResponseEntity<GameResponseDTO> gameStockOut(@PathVariable Long id){
        Game game = gameService.gameStockOut(id);
        return ResponseEntity.ok().body(GameMapper.toDto(game));
    }
}





















