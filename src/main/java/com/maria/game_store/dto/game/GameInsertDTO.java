package com.maria.game_store.dto.game;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameInsertDTO {

    @NotBlank(message = "A title is required")
    @Size(min = 4, max = 200, message = "The game description must be at least 4 characters and a maximum of 500 characters")
    private String title;

    @NotNull
    private String studio;

    @NotBlank(message = "A description is required")
    @Size(max = 400, message = "The game description must be a maximum of 400 characters")
    private String description;

    @NotNull
    private String genre;

    @NotEmpty
    @PositiveOrZero
    private Integer ageRating;

    @Positive
    @DecimalMin(value = "0")
    @DecimalMax(value = "999999.9999")
    private BigDecimal price;

    @PositiveOrZero
    private Integer stockQuantity;
}
