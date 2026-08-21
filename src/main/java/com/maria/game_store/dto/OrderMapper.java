package com.maria.game_store.dto;

import com.maria.game_store.model.entity.Order;
import org.modelmapper.ModelMapper;

public class OrderMapper {

    public static OrderResponseDTO toDto(Order order){
        return new ModelMapper().map(order, new OrderResponseDTO(order.getClient().getId(), order.getOrderDate(), order.getTotalPrice(), order.getStatus()).getClass());
    }

}
