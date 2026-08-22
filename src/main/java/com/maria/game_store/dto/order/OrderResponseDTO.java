package com.maria.game_store.dto.order;

import com.maria.game_store.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponseDTO {

    private Long clientId;

    private LocalDate orderDate;

    private BigDecimal totalPrice;

    private OrderStatus status;

}
