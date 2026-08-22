package com.maria.game_store.dto.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameResponseDTO {

    private Long id;

    private String title;

    private BigDecimal price;

    private Integer stockQuantity;
}
