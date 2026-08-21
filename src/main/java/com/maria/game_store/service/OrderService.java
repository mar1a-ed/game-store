package com.maria.game_store.service;

import com.maria.game_store.dto.OrderItemDTO;
import com.maria.game_store.dto.OrderUpdateDTO;
import com.maria.game_store.exception.OrderNotFoundException;
import com.maria.game_store.exception.UserNotFoundException;
import com.maria.game_store.exception.ZeroInventoryException;
import com.maria.game_store.model.entity.Client;
import com.maria.game_store.model.entity.Order;
import com.maria.game_store.model.entity.OrderItem;
import com.maria.game_store.model.enums.OrderStatus;
import com.maria.game_store.repository.ClientRepository;
import com.maria.game_store.repository.GameRepository;
import com.maria.game_store.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final GameRepository gameRepository;

    private final ClientRepository clientRepository;

    @Transactional
    public Order createOrder(Long clientId, List<OrderItemDTO> itemsDto) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new UserNotFoundException("Client not found.")
        );

        Order order = new Order();
        Double sum = 0.0;

        for(OrderItemDTO orderItems: itemsDto){
            OrderItem orderItem = new OrderItem();

            if(gameRepository.findByTitle(orderItems.getTitleGame()).getStockQuantity() > 0){
                orderItem.setGame(gameRepository.findByTitle(orderItems.getTitleGame()));
                orderItem.setQuantity(orderItems.getQuantity());
                orderItem.setUnitPrice(BigDecimal.valueOf(orderItems.getQuantity()).multiply(gameRepository.findByTitle(orderItems.getTitleGame()).getPrice()));
                orderItem.setOrder(order);

                order.getItems().add(orderItem);

                sum += Double.parseDouble(String.valueOf(orderItem.getUnitPrice()));

                gameRepository.findByTitle(orderItems.getTitleGame()).setStockQuantity(
                        gameRepository.findByTitle(orderItems.getTitleGame()).getStockQuantity() - orderItems.getQuantity()
                );

            }else{
                throw new ZeroInventoryException("The game is not available.");
            }
        }

        order.setClient(client);
        order.setStatus(OrderStatus.PROCESSING);
        order.setTotalPrice(BigDecimal.valueOf(sum));
        order.setOrderDate(LocalDate.now());

        orderRepository.save(order);

        return order;
    }

    @Transactional
    public Order updateOrderStatus(Long id, OrderUpdateDTO status){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Order not found.")
        );

        order.setStatus(OrderStatus.valueOf(status.getStatus()));

        orderRepository.save(order);

        return order;
    }

}
