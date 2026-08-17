package com.maria.game_store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameInsertDTO {

    private String title;

    private Double price;

    private Integer stockQuantity;
}
