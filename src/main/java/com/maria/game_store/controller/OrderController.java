package com.maria.game_store.controller;

import com.maria.game_store.dto.OrderItemDTO;
import com.maria.game_store.dto.OrderMapper;
import com.maria.game_store.dto.OrderResponseDTO;
import com.maria.game_store.model.entity.Order;
import com.maria.game_store.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create/{clientId}")
    public ResponseEntity<OrderResponseDTO> createOrder(@PathVariable Long clientId, @RequestBody List<OrderItemDTO> items){
        Order order = orderService.createOrder(clientId, items);
        OrderResponseDTO orderDto = OrderMapper.toDto(order);

        return ResponseEntity.ok().body(orderDto);
    }
}
