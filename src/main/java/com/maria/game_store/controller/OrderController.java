package com.maria.game_store.controller;

import com.maria.game_store.dto.OrderItemDTO;
import com.maria.game_store.dto.OrderMapper;
import com.maria.game_store.dto.OrderResponseDTO;
import com.maria.game_store.dto.OrderUpdateDTO;
import com.maria.game_store.model.entity.Order;
import com.maria.game_store.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create/{clientId}")
    public ResponseEntity<OrderResponseDTO> createOrder(@PathVariable Long clientId, @RequestBody @Valid List<OrderItemDTO> items){
        Order order = orderService.createOrder(clientId, items);
        OrderResponseDTO orderDto = OrderMapper.toDto(order);

        return ResponseEntity.ok().body(orderDto);
    }

    @PatchMapping("/{id}/update/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Long id, @RequestBody @Valid OrderUpdateDTO status){
        Order order = orderService.updateOrderStatus(id, status);
        OrderResponseDTO orderDto = OrderMapper.toDto(order);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderDto);
    }

    @PatchMapping("/{id}/update/payment")
    public ResponseEntity<OrderResponseDTO> updateOrderPaymentStatus(@PathVariable Long id, @RequestBody @Valid OrderUpdateDTO payment){
        Order order = orderService.updateOrderPaymentStatus(id, payment);
        OrderResponseDTO orderDto = OrderMapper.toDto(order);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderDto);
    }
}
