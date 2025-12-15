package com.example.starbucks.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.starbucks.dao.OrderDao;
import com.example.starbucks.dto.CartItemDto;
import com.example.starbucks.dto.OrderDto;
import com.example.starbucks.dto.StoreDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    public List<StoreDto> getNearest(double lat, double lng) {
        return orderDao.findNearestStores(lat, lng);
    }

    public StoreDto getStoreById(int id) {
        return orderDao.getStoreById(id);
    }

    @Transactional
    public void saveOrder(List<CartItemDto> cart) {

        // int totalPrice = cart.stream()
        //         .mapToInt(CartItemDto::getTotalPrice)
        //         .sum();

        // 1️⃣ 주문 저장
        OrderDto order = new OrderDto();
        order.setStoreId(cart.get(0).getStoreId());
        order.setOrderType(cart.get(0).getOrderType());
        // order.setTotalPrice(totalPrice);
        order.setOrderStatus("ORDERED");

        orderDao.insertOrder(order);

        // int orderId = order.getId(); // useGeneratedKeys

        // 2️⃣ 주문 상세 저장
        // for (CartItemDto item : cart) {
        //     orderDao.insertOrderItem(orderId, item);
        // }
    }
    
}
