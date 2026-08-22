package com.maria.game_store.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO {

    @NotBlank(message = "A title is required")
    private String titleGame;

    @NotEmpty(message = "A quantity is required")
    @Positive(message = "The quantity must be higher than zero.")
    private Integer quantity;

    @NotNull
    private String paymentMethod;
}
