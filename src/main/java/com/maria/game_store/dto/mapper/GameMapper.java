package com.maria.game_store.dto.mapper;

import com.maria.game_store.dto.game.GameInsertDTO;
import com.maria.game_store.dto.game.GameResponseDTO;
import com.maria.game_store.model.entity.Game;
import org.modelmapper.ModelMapper;

public class GameMapper {

    public static Game toGame(GameInsertDTO dto){
        return new ModelMapper().map(dto, Game.class);
    }

    public static GameInsertDTO toGameDto(Game game){
        return new ModelMapper().map(game, GameInsertDTO.class);
    }

    public static GameResponseDTO toDto(Game game){
        return new ModelMapper().map(game, new GameResponseDTO(game.getId(), game.getTitle(), game.getPrice(), game.getStockQuantity()).getClass());
    }
}
